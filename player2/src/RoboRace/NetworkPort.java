/*this is the player network handleer
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoboRace;

import java.io.*;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author John Phantom Li
 */
public class NetworkPort implements Port {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public NetworkPort(Socket socket) {
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        };

    }

    @Override
    public void send(String message) {
        if (out != null) {
            out.println(message);
            out.println("\0");
            out.flush();
        };
    }

    @Override
    public String recieve() {
            String str = "";
            String line = "";
            if (in != null && out != null) {

                while (!line.equals("\0")) {
                    str += line;
                    try{
                         line = in.readLine();
                    }catch(IOException e){
                        
                    }
                }
            }
        return str;
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
