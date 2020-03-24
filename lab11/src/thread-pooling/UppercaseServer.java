// UppercaseServer.java
import java.io.*;
import java.net.*;

public class UppercaseServer {
    public static void main(String[] args) throws Exception {
      if (args.length != 1) {
          System.out.println("Usage: java UppercaseServer <portNum>");
          System.exit(1);
      }

      int portNum = Integer.parseInt(args[0]);
      ServerSocket serverSocket = new ServerSocket(portNum);
        
        while(true) {
            Socket requestSocket = serverSocket.accept();
            Thread serverThread 
                = new Thread(new UppercaseWorker(requestSocket));
            serverThread.start();
        }
    }
}

class UppercaseWorker implements Runnable {
    private Socket _requestSocket;

    public UppercaseWorker(Socket requestSocket) throws IOException {
        System.out.println("Creating new worker");
        _requestSocket = requestSocket;
    }

    public void run() {
        BufferedReader requestReader = null;
        Writer responseWriter = null;
        try {
            requestReader
                = new BufferedReader(
                      new InputStreamReader(_requestSocket.getInputStream()));
            responseWriter
                = new OutputStreamWriter(_requestSocket.getOutputStream());

            while(true) {
                String requestString = requestReader.readLine();
                if (requestString == null) {
                    break;
                }
                System.out.println("Got request: " + requestString);
                responseWriter.write(requestString.toUpperCase() + "\n");
                responseWriter.flush();
            }
        } catch(IOException ex) {
        } finally {
            try {
                if (responseWriter != null) {
                    responseWriter.close();
                }
                if (requestReader != null) {
                    requestReader.close();
                }
                _requestSocket.close();
            } catch (IOException ex2) {
            }
        }
        System.out.println("Ending the session");
    }
}
