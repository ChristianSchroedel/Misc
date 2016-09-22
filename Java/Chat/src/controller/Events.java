/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

/**
 *
 * @author christian
 */
public enum Events
{
    CONNECT_CLIENT_SUCCESSFUL,
    CONNECT_CLIENT_FAILED,
    
    CONNECT_SERVER_SUCCESSFUL,
    CONNECT_SERVER_FAILED,
    
    DISCONNECTED,
    
    MESSAGE_RECEIVED,
    POINTS_RECEIVED;
}
