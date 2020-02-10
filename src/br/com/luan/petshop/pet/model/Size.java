package br.com.luan.petshop.pet.model;

public enum Size {
    SMALL,
    MEDIUM,
    BIG;

    public static Size getSizeByIndex(Integer index) {
        switch (index) {
            case 1: return SMALL;
            case 2: return MEDIUM;
            default: return BIG;
        }
    }
}
