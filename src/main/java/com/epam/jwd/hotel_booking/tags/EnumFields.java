package com.epam.jwd.hotel_booking.tags;

import com.epam.jwd.hotel_booking.model.enums.FoodPlan;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class EnumFields extends TagSupport {
    private String var;
    private String enumName;
    private String enumClass;

    public void setVar(String var) {
        this.var = var;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public void setEnumClass(String enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public int doStartTag() throws JspException {
        switch (enumClass) {
            case ("RoomSize"):
                pageContext.setAttribute(var, RoomSize.ofString(enumName));
                break;
            case ("RoomType"):
                pageContext.setAttribute(var, RoomType.ofString(enumName));
                break;
            case ("FoodPlan"):
                pageContext.setAttribute(var, FoodPlan.ofString(enumName));
                break;
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
