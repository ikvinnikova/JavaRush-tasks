package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random()*100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ")) {
                String name = message.split(": ")[0];
                String text = message.split(": ")[1];
                Calendar calendar = new GregorianCalendar();
                SimpleDateFormat dateFormat;
                switch (text) {
                    case "дата":
                        dateFormat = new SimpleDateFormat("d.MM.YYYY");
                        sendTextMessage("Информация для " + name + ": " + dateFormat.format(calendar.getTime()));
                        break;
                    case "день":
                        dateFormat = new SimpleDateFormat("d");
                        sendTextMessage("Информация для " + name + ": " + dateFormat.format(calendar.getTime()));
                        break;
                    case "месяц":
                        dateFormat = new SimpleDateFormat("MMMM");
                        sendTextMessage("Информация для " + name + ": " + dateFormat.format(calendar.getTime()));
                        break;
                    case "год":
                        dateFormat = new SimpleDateFormat("YYYY");
                        sendTextMessage("Информация для " + name + ": " + dateFormat.format(calendar.getTime()));
                        break;
                    case "время":
                        dateFormat = new SimpleDateFormat("H:mm:ss");
                        sendTextMessage("Информация для " + name + ": " + dateFormat.format(calendar.getTime()));
                        break;
                    case "час":
                        dateFormat = new SimpleDateFormat("H");
                        sendTextMessage("Информация для " + name + ": " + dateFormat.format(calendar.getTime()));
                        break;
                    case "минуты":
                        dateFormat = new SimpleDateFormat("m");
                        sendTextMessage("Информация для " + name + ": " + dateFormat.format(calendar.getTime()));
                        break;
                    case "секунды":
                        dateFormat = new SimpleDateFormat("s");
                        sendTextMessage("Информация для " + name + ": " + dateFormat.format(calendar.getTime()));
                        break;
                    default:
                        break;
                }
            }
        }
    }
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
