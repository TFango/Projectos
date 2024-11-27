package Trabajo.Personajes;

import Trabajo.Enemigos.Enemigo;

    public class Arquero extends Personaje {

        private int precision;
        private int agilidad;


        public Arquero(String nombre) {
            super(nombre, 1, 90, 15, 10, 12);
            this.precision = 20;
            this.agilidad = 25;
        }

        @Override
        public void atacar(Enemigo enemigo) {
            enemigo.recibirDaño(this.fuerza);
        }

        public int getPrecision() {
            return precision;
        }

        public int getAgilidad() {
            return agilidad;
        }

        public void setPrecision(int precision) {
            this.precision = precision;
        }

        public void setAgilidad(int agilidad) {
            this.agilidad = agilidad;
        }



    }

