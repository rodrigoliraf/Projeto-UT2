package algoritmos;

import interfaces.ICompareData;

public abstract class BaseAlgorithm {
    public abstract String[] ordenar(String[] arr, ICompareData compareTo, String typeSort);
    public abstract String getName();
}
