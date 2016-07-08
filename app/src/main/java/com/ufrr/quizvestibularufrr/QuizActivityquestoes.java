package com.ufrr.quizvestibularufrr;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ufrr.quizvestibularufrr.dataBase.Dados;
import com.ufrr.quizvestibularufrr.dataBase.DataBase;
import com.ufrr.quizvestibularufrr.dataBase.Repositorio;
import com.ufrr.quizvestibularufrr.questionsClass.Question;
import com.ufrr.quizvestibularufrr.questionsClass.QuestionDateBase;
import com.ufrr.quizvestibularufrr.utils.MyDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuizActivityquestoes extends ActionBarActivity implements View.OnClickListener {

    private final Handler handler = new Handler();
    private TextView tvNumber, tvQuestion, tvResponse;
    private Button bChoice1, bChoice2, bChoice3, bChoice4, b_restart;
    private Toolbar toolbar;
    private Animation shakeAnimation;

    private QuestionDateBase dbQuestionDateBase;
    private List<Question> qList;
    private Question currentQ;

    private MyDatabase dbMyDatabase;
    private SQLiteDatabase conn;

    private LinearLayout buttonContainer;
    private int index;
    private int score = 0;
    private int qPoints;

    private DataBase dataBase;
    private SQLiteDatabase connSqLiteDatabase;
    private Repositorio repositorio;
    private Dados dados;

    private SimpleDateFormat df;
    private String data = "date";
    private Calendar calendar = Calendar.getInstance();

    private String quizSelecionado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNumber = (TextView) findViewById(R.id.tv_number);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
        tvResponse = (TextView) findViewById(R.id.tv_response);
        buttonContainer = (LinearLayout) findViewById(R.id.button_container);
        b_restart = (Button) findViewById(R.id.b_restart);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.incorrect_shake);
        shakeAnimation.setRepeatCount(3);

        bChoice1 = (Button) findViewById(R.id.button1);
        bChoice2 = (Button) findViewById(R.id.button2);
        bChoice3 = (Button) findViewById(R.id.button3);
        bChoice4 = (Button) findViewById(R.id.button4);

        bChoice1.setOnClickListener(this);
        bChoice2.setOnClickListener(this);
        bChoice3.setOnClickListener(this);
        bChoice4.setOnClickListener(this);
        b_restart.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        quizSelecionado = extras.getString("QUIZSLECIONADO");

        try {
            dataBase = new DataBase(this);
            connSqLiteDatabase = dataBase.getWritableDatabase();
            repositorio = new Repositorio(connSqLiteDatabase);
        } catch(SQLException ex)
        {
            Toast.makeText(this, "Erro ao criar o Banco de dados: " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        setupUI();
        startQuiz();
    }

    // Retrieve fresh batch of questions and reset UI / quiz related values
    private void startQuiz() {

        //getQuestions();
        index = 0;
        score = 0;
        qPoints = 10;
        b_restart.setVisibility(View.INVISIBLE);
        tvResponse.setVisibility(View.INVISIBLE);
        setQuestionView();
    }

    // Set the view for each question
    private void setQuestionView() {

        if (index < qList.size()) {
            qPoints = 10;
            currentQ = qList.get(index);
            int qId = index + 1;

            tvResponse.setVisibility(View.INVISIBLE);
            enableButtons();

            tvNumber.setText("Question " + qId + "/" + qList.size());

            tvQuestion.setText(currentQ.getText());

            bChoice1.setText(currentQ.getChoiceList().get(0));
            bChoice2.setText(currentQ.getChoiceList().get(1));
            bChoice3.setText(currentQ.getChoiceList().get(2));
            bChoice4.setText(currentQ.getChoiceList().get(3));

            index++;

        } else {
            b_restart.setVisibility(View.VISIBLE);
            tvResponse.setText("Sua pontuação final é:" + score + " de 100");
            b_restart.setText("JOAR NOVAMENTE");

            salvarInformacoesBancoDADOS();

        }
    }


    public void salvarInformacoesBancoDADOS(){
        try
        {
            df = new SimpleDateFormat("dd/MM/yyyy"); //"dd-MM-yyyy HH:mm:ss"  Alterado dd/MM/yyyy para dd/MM
            data = df.format(calendar.getTime());
                    Log.i("salvarInformacoes", "DATA - Data Novo Formato: " + data);
            String Jogador = "Jogador";

            //dados.setNome(String.valueOf(data));

            dados = new Dados();

            dados.setNome(String.valueOf(Jogador));
                    Log.v("salvarInformacoes", "dados.setNome: JORGADOR");
            dados.setQuizName(String.valueOf(quizSelecionado));
                    Log.v("salvarInformacoes", "setQuizName: " + quizSelecionado);
            dados.setPontos(String.valueOf(score));
                    Log.v("salvarInformacoes", "dados.setPontos: " + score);
            dados.setData(String.valueOf(data));
                    Log.v("salvarInformacoes", "dados.setData: " + data);


            if (dados.getId() == 0){
                repositorio.inserirDados(dados);
            }

        } catch(Exception ex)
        {
            Toast.makeText(this, "Erro ao salvar os dados: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            Log.i("LOG", "Erro ao salvar os dados: " + ex.getMessage());
        }
    }

    // Disable all buttons so that they cannot be clicked
    public void disableButtons() {

        for (int i = 0; i < buttonContainer.getChildCount(); i++) {
            Button button = (Button) buttonContainer.getChildAt(i);
            button.setClickable(false);
        }
    }

    // Enable all buttons
    public void enableButtons() {

        for (int i = 0; i < buttonContainer.getChildCount(); i++) {
            Button button = (Button) buttonContainer.getChildAt(i);
            button.setEnabled(true);
            button.setClickable(true);
            button.setTextColor(getResources().getColor(R.color.black));
        }
    }

    // Button functions
    @Override
    public void onClick(View v) {

        Button button = (Button) findViewById(v.getId());

        if (button.getText().equals("Restart")) {
            startQuiz();
        } else if (currentQ.getAnswer().equals(button.getText())) {
            score = score + qPoints;
            tvResponse.setVisibility(View.VISIBLE);
            tvResponse.setText("CORRECT!");
            button.setTextColor(getResources().getColor(R.color.green));
            disableButtons();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setQuestionView();
                }
            }, 1000);

        } else {
            qPoints = qPoints - 3;
            tvResponse.setVisibility(View.VISIBLE);
            tvResponse.setText("INCORRECT!");
            button.startAnimation(shakeAnimation);
            button.setTextColor(getResources().getColor(R.color.red));
            button.setClickable(false);
        }
    }


    public void setupUI() {
        //Acesso ao banco de dados de Perguntas externa
        try {
            dbMyDatabase = new MyDatabase(this);
            conn = dbMyDatabase.getWritableDatabase();
            dbQuestionDateBase = new QuestionDateBase(this, conn);
        } catch (SQLException ex) {
            Toast.makeText(this, "ERRO AO ACESSAR O BANCO DE DADOS DA APLICAÇÃO: " + ex, Toast.LENGTH_LONG).show();
        }

        //qList = new ArrayList<>(dbQuestionDateBase.getTenQuestions());
        qList = new ArrayList<>(dbQuestionDateBase.getTenQuestoesBancoDados(quizSelecionado));
        dbQuestionDateBase.close();

    }
}
