/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author RhoLouh
 */
@Named(value = "employeeBean")
@Dependent
public class EmployeeBean {

    /**
     * Creates a new instance of EmployeeBean
     */
    public EmployeeBean() {
    }
    
    
}
