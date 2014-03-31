package jeu.apprentissage;

/**
 * Created by Jicé on 31/03/2014.
 */
public enum Caractere {
    A(new int[] {1}),
    B(new int[] {1, 3}),
    C(new int[] {1, 2}),
    D(new int[] {1, 2, 4}),
    E(new int[] {1, 4}),
    F(new int[] {1, 2, 3}),
    G(new int[] {1, 2, 3, 4}),
    H(new int[] {1, 3, 4}),
    I(new int[] {2, 3}),
    J(new int[] {2, 3, 4}),
    K(new int[] {1, 5}),
    L(new int[] {1, 3, 5}),
    M(new int[] {1, 2, 5}),
    N(new int[] {1, 2, 4, 5}),
    O(new int[] {1, 4, 5}),
    P(new int[] {1, 2, 3, 5}),
    Q(new int[] {1, 2, 3, 4, 5}),
    R(new int[] {1, 3, 4, 5}),
    S(new int[] {2, 3, 5}),
    T(new int[] {2, 3, 4, 5}),
    U(new int[] {1, 5, 6}),
    V(new int[] {1, 3, 5, 6}),
    W(new int[] {2, 3, 4, 6}),
    X(new int[] {1, 2, 5, 6}),
    Y(new int[] {1, 2, 4, 5, 6}),
    Z(new int[] {1, 4, 5, 6});

    int[] points;
    Caractere(int[] i) {
        points = i;
    }

    public int[] getPoints() {
        return points;
    }
}
