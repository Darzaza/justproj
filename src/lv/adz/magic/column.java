package lv.adz.magic;

 enum Columns {

        colOne(0),
        colTwo(1),
        colThree(2);

        private int value;

        public int getValue(){
            return value;
        }


    Columns(int value){
            this.value = value;
    }
}
