package com.example.wave.Repository;

public class CartRepository {

        private static CartRepository instance;

        private CartRepository() {
        }

        public static synchronized CartRepository getInstance() {
            if (instance == null) {
                instance = new CartRepository();
            }
            return instance;
        }
}
