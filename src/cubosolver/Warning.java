/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubosolver;

/**
 *
 * @author Fran
 */
public class Warning {
    String message;

    public Warning(String message) {
        this.message = message;
    }

    public Warning() {
        this("");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
