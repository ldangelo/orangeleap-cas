package com.orangeleap.security.spring;

import org.springframework.web.servlet.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.Writer;
import java.text.DateFormat;

/**
 * Implementation of a Spring MVC View that renders the model as a JSON String. This
 * class supports the Script Tag Proxy mechanism, used by popular JavaScript libraries
 * like ExtJS, to allow for cross domain XHR requests.
 *
 * @version 1.0
 */
public class JsonView implements View {

    // Mime type to use with the Script Tag mechanism
    private final static String SCRIPTTAG_MIME_TYPE = "text/javascript";

    // Default mime type for JSON data
    private final static String JSON_MIME_TYPE = "text/javascript";

    /**
     * Name of Map key used to denote a root level replacement object so that
     * a ModelMap can produce a JSON array (or anything other than a map)
     */
    public final static String ROOT = "_root";

    private boolean encodeNulls = true;

    private String dateFormat = "yyyy-MM-dd";

    /**
     * Name of the callback method parameter name used in Script Tag Proxy configuration.
     * Default is 'callback'
     */
    private String callbackMethodName = "callback";

    private String contentType = JSON_MIME_TYPE;

    public void setContentType(String type) {
        this.contentType = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setDateFormat(String format) {
        this.dateFormat = format;
    }

    /**
     * The meat of the View. This method takes the model as a Map, encodes it as JSON, and
     * writes it out to the response with the correct encoding.
     *
     * @param model    the Map containing the data to encode as JSON
     * @param request  the incoming HttpServletRequest
     * @param response the HttpServletResponse the JSON data will the written to
     * @throws Exception if bad stuff happens
     */
    @SuppressWarnings("unchecked")
    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            StringBuilder json = new StringBuilder();

            GsonBuilder builder = new GsonBuilder();
            builder.setDateFormat(dateFormat);
            builder.setPrettyPrinting();

            if (encodeNulls) {
                builder.serializeNulls();
            }

            Gson gson = builder.create();

            if (model.containsKey(ROOT)) {
                // If the map contains a key ROOT, use that object
                // as the top level object instead of a map.
                // This allows the JsonView to return an array instead
                // of only an object
                Object root = model.get(ROOT);

                gson.toJson(root,json);
            } else {
                removeSpringFields(model);
                gson.toJson(model,json);
            }


            String ret = handleCallback(request, json.toString());

            response.setContentType(getContentType());
            Writer out = response.getWriter();
            out.write(ret);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void removeSpringFields(Map<String,Object> map) {

        // list of keys to remove
        List<String> keys = new ArrayList<String>();

        for(String key : map.keySet()) {
            if(key.startsWith("org.springframework")) {
                keys.add(key);
            }
        }

        for(String key : keys) {
            map.remove(key);
        }
    }


    /**
     * Sets the name of the parameter to retrieve from the HttpServletRequest
     * to get the callback method name from.
     *
     * @param name the name of the parameter containing the name of the callback method
     */
    public void setCallbackMethodName(String name) {
        callbackMethodName = name;
    }

    /**
     * Tells the BeanEncoder whether it should encode properties that return null
     * values. Default behavior is to encode the nulls as empty double-quotes. Setting
     * this to false will cause null properties to be skipped
     *
     * @param encode true to encode null values, false to omit
     */
    public void setEncodeNullBeanProperties(boolean encode) {
        this.encodeNulls = encode;
    }


    /**
     * Helper method to deal with Script Tag Proxy. It looks for requests parameter
     * called 'callback', and if it is present, it uses that value to create a JavaScript
     * callback method which wraps the JSON data being returned. It also sets the
     * correct mime type. If the callback parameter is not found, this method
     * returns the original json String.
     *
     * @param request the HttpServletRequest
     * @param json    the String of JSON data to be returned
     * @return the JSON String to return to the caller.
     */
    String handleCallback(HttpServletRequest request, String json) {
        String cb = request.getParameter(callbackMethodName);

        if (cb != null) {
            setContentType(SCRIPTTAG_MIME_TYPE);
            StringBuilder builder = new StringBuilder();
            builder.append(cb).append("(").append(json).append(");");
            return builder.toString();
        } else {
            return json;
        }
    }

}
