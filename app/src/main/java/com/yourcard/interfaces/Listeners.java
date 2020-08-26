package com.yourcard.interfaces;

public interface Listeners {
    interface BackListener {
        void back();
    }

    interface LoginListener {
        void validate();

        void showCountryDialog();
    }

    interface SignUpListener {


        void checkDataValid();

    }
    interface CardDetialsAction{
        void choosecard();
    }

    interface ProfileActions {
        void order();

        void charge();


        void aboutApp();

        void logout();

        void favorite();

        void bankAccount();
        void profile();



    }

}
