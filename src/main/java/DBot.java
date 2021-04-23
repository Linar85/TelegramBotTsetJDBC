import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class DBot extends TelegramLongPollingBot {
    private String chat_id;
    private String nameUser = "";

    Login login = new Login();

    private InlineKeyboardMarkup getBut() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton but1 = new InlineKeyboardButton().setText("DELETE");
        but1.setCallbackData("Deleted");
        List<InlineKeyboardButton> row1 = new ArrayList<InlineKeyboardButton>();
        row1.add(but1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<List<InlineKeyboardButton>>();
        rowList.add(row1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


    @Override
    public void onUpdateReceived(Update update) {
        //update.getUpdateId();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage sendMessage = new SendMessage().setChatId(chat_id).setText(text);

            if (text.equals("/get")) {
                for (String x :login.get()) {
                    sendMessage.setText(x).setReplyMarkup(getBut());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                login.add(text);
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            long mess_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (data.equals("Deleted")){
                String backMess = update.getCallbackQuery().getMessage().getText();
                login.delete(backMess);

            }


            }

        }


        @Override
        public String getBotUsername () {
            return "ufanetTeatBot";
        }

        @Override
        public String getBotToken () {
            return "1368155300:AAGM0qVdSsrH5NBhIh1Xf2BekrfydchHUAg";
        }
    }
