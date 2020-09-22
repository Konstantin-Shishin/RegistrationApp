package com.example.registrationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

      // Обрабатываем нажатие кнопки "Приветствие":
    public void Hello(View view) {

        String fullName = "Привет," + MainActivity.name;
        Dialog dialog = new Dialog(Second.this);
        // Передаем ссылку на разметку
        dialog.setContentView(R.layout.dialog_hello);
        // Находим элемент TextView внутри разметки
        // и устанавливаем ему соответствующий текст
        TextView textView = (TextView) dialog.findViewById(R.id.hello_text);
        textView.setText(fullName);
// Выводим диалоговое окно на экран
        dialog.show();
    }
}
