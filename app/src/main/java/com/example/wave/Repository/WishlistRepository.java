package com.example.wave.Repository;

public class WishlistRepository {

        private static WishlistRepository instance;

        private WishlistRepository() {
        }

        public static synchronized WishlistRepository getInstance() {
            if (instance == null) {
                instance = new WishlistRepository();
            }
            return instance;
        }
}
