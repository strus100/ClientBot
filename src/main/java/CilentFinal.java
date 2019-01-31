import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class CilentFinal {
    public static List listAtak = new ArrayList();
    public static void main(String argv[]) throws Exception {
        String sendto;
        String recvfrom;
        StringTokenizer stringTokenizer;
        List list = new ArrayList();



        String threadID = "0";
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

        outToServer.writeBytes("LOGIN " + sendto + '\n');

        recvfrom = inFromServer.readLine();

        stringTokenizer = new StringTokenizer(recvfrom);

        while (stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }
        threadID = list.get(1).toString();
        System.out.println("FROM SERVER: " + recvfrom);

        recvfrom = inFromServer.readLine();
        System.out.println("FROM SERVER: " + recvfrom);


        for (int i = 0; i < 25; i++) {
            recvfrom = inFromServer.readLine();

            String string = CilentFinal.saveAtak(recvfrom, threadID);
            if(!string.equals("null")){
                listAtak.add(string);}
                System.out.println("FROM SERVER: " + recvfrom);
        }

        recvfrom = inFromServer.readLine();
        System.out.println("FROM SERVER: " + recvfrom);

        //KONIEC STARTU


        while (true) {
       listAtak.clear();
            for (int i = 0; i < listAtak.size(); i++) {


                sendto = sendAtak(listAtak.get(i).toString());


                List mylist = new ArrayList<String>();
                stringTokenizer = new StringTokenizer(sendto);

                mylist.clear();
                while (stringTokenizer.hasMoreTokens()) {
                    mylist.add(stringTokenizer.nextToken());
                }

                if (mylist.get(0) == "ATAK" && mylist.size() == 5) {
                    System.out.println(sendto);
                    outToServer.writeBytes(sendto + '\n');

                    recvfrom = inFromServer.readLine();
                    System.out.println("FROM SERVER: " + recvfrom);

                    recvfrom = inFromServer.readLine();
                    System.out.println("FROM SERVER: " + recvfrom);
                }
            }
            sendto = "PASS";
            System.out.println(sendto);
            outToServer.writeBytes(sendto + '\n');


            recvfrom = inFromServer.readLine();
            System.out.println("FROM SERVER: " + recvfrom);

            recvfrom = inFromServer.readLine();
            System.out.println("FROM SERVER: " + recvfrom);

            if (sendto.equalsIgnoreCase("PASS")) {

                for (int i = 0; i < 25; i++) {
                    recvfrom = inFromServer.readLine();
                    String string = CilentFinal.saveAtak(recvfrom, threadID);
                    if(!string.equals("null")){
                        listAtak.add(string);}

                    System.out.println("FROM SERVER: " + recvfrom);

                }


            }

            recvfrom = inFromServer.readLine();
            System.out.println("FROM SERVER: " + recvfrom);


        }
    }

    public static String sendAtak(String savedAtak) {


        StringTokenizer stringTokenizer;
        List list = new ArrayList<String>();
        stringTokenizer = new StringTokenizer(savedAtak);

        list.clear();
        while (stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }

        int x = Integer.parseInt(list.get(0).toString());
        int y = Integer.parseInt(list.get(1).toString());
        int dices = Integer.parseInt(list.get(2).toString());
        String sendto = "ATAK " + x + " " + y + " ";
        if (x < 5) {
            x++;
        }
        String temp = x + " " + y;

        if(!listAtak.equals(temp)){

            if(dices>1){
                sendto += temp;
            }
            else{sendto = "PASS";}
        }else{sendto = "PASS";}
        return sendto;
    }

    public static String saveAtak(String recvfrom, String threadID) {

        String add = "null";
        StringTokenizer stringTokenizer;
        List list = new ArrayList<String>();

        stringTokenizer = new StringTokenizer(recvfrom);

        list.clear();
        while (stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }

        System.out.println(list);
        if (list.get(0).equals("PLANSZA")) {
            if (list.get(3).equals(threadID)) {
               add = list.get(1).toString()+ " " + list.get(2).toString() + " " + list.get(4).toString();

                System.out.println("ADD" + add);

            }
        }
        return add;
    }
}





