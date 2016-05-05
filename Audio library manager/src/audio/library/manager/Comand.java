/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import java.io.IOException;

/**
 *
 * @author tifui
 */
public interface Comand {
    
    void execute() throws IOException,IllegalArgumentException,ComandArgumentException;
    
}
