package com.orangeleap.security.spring;

import org.springframework.web.servlet.RequestToViewNameTranslator;
import javax.servlet.http.HttpServletRequest;

/**
 * Translator used for JSON requests to route all views to the JsonView. The ref
 * to the defined JsonView is pased to this translator.
 * @version 1.0
 */
public class JsonRequestToViewNameTranslator implements RequestToViewNameTranslator {

    private String view = "";

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getViewName(HttpServletRequest request) throws Exception {
        return view;
    }
}
