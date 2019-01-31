import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class atakBot {
    public static void main(String argv[]) throws Exception {
        String sendto;
        String recvfrom;

        String atak ="wsxdcfrtgbhjuik,kjuhygvfcfr wsxdcfrtgbhjuik,kjuhygvfcfrwsxdcfrtgbhjuik,kjuhygvfcfrwsxdcfrtgbhjuik,kjuhygvfcfrwsxdcfrtgbhjuik,kjuhygvfcfrwsxdcfrtgbhjuik,kjuhygvfcfr";

        for (int i = 0; i <10 ; i++) {
            atak += atak;
        }


        System.out.println("Czekam na rozpoczęcie gry..");
        //Tworzenie socketu
        Socket clientSocket = new Socket("localhost", 3333);


        //Komunikacja od servera
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //Komunikacja do servera
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //Wpisz z klawy testy...
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        recvfrom = inFromServer.readLine();
        System.out.println("FROM SERVER: " + recvfrom);

        System.out.println("Podaj login: ");
        sendto = inFromUser.readLine();
        outToServer.writeBytes("LOGIN " + sendto +'\n');


        for (int i = 0; i < 20 ; i++) {
            recvfrom = inFromServer.readLine();
            System.out.println("FROM SERVER: " + recvfrom);

        }


        //KONIEC STARTU


        List list = new ArrayList<String>();
        while (true){

            System.out.println("Wpisz coś: ");

            sendto = atak;
            outToServer.writeBytes(sendto + '\n');



            recvfrom = inFromServer.readLine();
            System.out.println("FROM SERVER: " + recvfrom);

            recvfrom = inFromServer.readLine();
            System.out.println("FROM SERVER: " + recvfrom);

            if(sendto.equalsIgnoreCase( "PASS")) {

                for (int i = 0; i < 26; i++) {
                    recvfrom = inFromServer.readLine();
                    System.out.println("FROM SERVER: " + recvfrom);

                }


            }


        }
    }
}






