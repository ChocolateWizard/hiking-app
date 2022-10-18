/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borak.hikingapp.commonlib.view.components.api;


import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.awt.Color;

/**
 *
 * @author Despot
 */
public interface IComponent<ComponentType> {

    //sets the main caption to given String
    void setCaption(String caption);

    //sets the main caption and all bonus captions to given Strings.
    //When setting, it goes from top-left, to bottom-right of component
    void setCaption(String[] caption);

    //sets the width of main caption
    void setCaptionSize(int width);

    //sets width and height of main caption
    void setCaptionSize(int width, int height);

    //sets the main errorMessage to given String
    void setErrorMessage(String errorMessage);

    //sets the width of errorMessage
    void setErrorMessageSize(int width);

    //sets the width and height of errorMessage
    void setErrorMessageSize(int width, int height);

    //returns the value stored in the component. This value depends on the Type of component
    ComponentType getValue() throws CustomException;

    //sets the stored value in the component. This value depends on the Type of component
    void setValue(ComponentType value) throws CustomException;

    //sets the width of input field
    void setInputSize(int width);

    //sets the width and height of input field
    void setInputSize(int width, int height);

    //sets the width of all input fields
    //When setting, it goes from top-left, to bottom-right of component
    void setInputSize(Integer[] width);

    //sets the width and height of all input fields
    //When setting, it goes from top-left, to bottom-right of component
    void setInputSize(Integer[] width, Integer[] height);

    //sets the background color of component
    void setBackgroundColor(Color color);
    
    //decides whether or not this component is enabled
    void setEnabledInput(boolean isEnabled);
}
