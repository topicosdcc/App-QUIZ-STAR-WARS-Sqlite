package com.ufrr.quizvestibularufrr;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ufrr.quizvestibularufrr.libraryMaterialRipple.MaterialRippleLayout;

public class FragmentTab1 extends Fragment {

    private Animatable animatable;
    private Animatable animatablePersonagem;
    private MaterialRippleLayout materialRippleLayoutQuizI;
    private MaterialRippleLayout materialRippleLayoutQuizII;
    private MaterialRippleLayout materialRippleLayoutQuizIII;
    private String quizSlecionado = null;


    public static FragmentTab1 newInstance() {
        FragmentTab1 fragment = new FragmentTab1();
        return fragment;
    }

    public FragmentTab1() {
        // Deve existir um construtor vazio
        // na classe que estende um Fragment
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tabs1, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.Imagem_Estrela);
        animatable = (AnimationDrawable) imageView.getDrawable();
        animatable.start();

        ImageView imageViewPersonagem = (ImageView) view.findViewById(R.id.Imagem_Personagem);
        animatablePersonagem = (AnimationDrawable) imageViewPersonagem.getDrawable();
        animatablePersonagem.start();


        materialRippleLayoutQuizI = (MaterialRippleLayout) view.findViewById(R.id.MaterialRippleLayoutQuizI);
        materialRippleLayoutQuizII = (MaterialRippleLayout) view.findViewById(R.id.MaterialRippleLayoutQuizII);
        materialRippleLayoutQuizIII = (MaterialRippleLayout) view.findViewById(R.id.MaterialRippleLayoutQuizIII);

        materialRippleLayoutQuizI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getActivity(), QuizActivityquestoes.class));
                /*
                Intent intent = new Intent(getActivity(), QuizActivityquestoes.class);
                quizSlecionado = "StarWars_BasicoI";
                intent.putExtra("QUIZSLECIONADO", quizSlecionado);
                startActivity(intent);
                */
                StartActivity("StarWars_BasicoI");
            }
        });

        materialRippleLayoutQuizII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity("StarWars_BasicoII");
            }
        });
        materialRippleLayoutQuizIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity("StarWars_BasicoIII");
            }
        });

        return view;
    }

    public void StartActivity(String QuizSelecionado){

        Intent intent = new Intent(getActivity(), QuizActivityquestoes.class);
        quizSlecionado = QuizSelecionado;
        intent.putExtra("QUIZSLECIONADO", quizSlecionado);
        startActivity(intent);
    }

}
