package jeu.apprentissage;

import java.awt.event.KeyEvent;

/**
 * Created by Jicé on 31/03/2014.
 */
public enum Caractere {
    A(new int[] {1}, KeyEvent.VK_A),
    B(new int[] {1, 3}, KeyEvent.VK_B),
    C(new int[] {1, 2}, KeyEvent.VK_C),
    D(new int[] {1, 2, 4}, KeyEvent.VK_D),
    E(new int[] {1, 4}, KeyEvent.VK_E),
    F(new int[] {1, 2, 3}, KeyEvent.VK_F),
    G(new int[] {1, 2, 3, 4}, KeyEvent.VK_G),
    H(new int[] {1, 3, 4}, KeyEvent.VK_H),
    I(new int[] {2, 3}, KeyEvent.VK_I),
    J(new int[] {2, 3, 4}, KeyEvent.VK_J),
    K(new int[] {1, 5}, KeyEvent.VK_K),
    L(new int[] {1, 3, 5}, KeyEvent.VK_L),
    M(new int[] {1, 2, 5}, KeyEvent.VK_M),
    N(new int[] {1, 2, 4, 5}, KeyEvent.VK_N),
    O(new int[] {1, 4, 5}, KeyEvent.VK_O),
    P(new int[] {1, 2, 3, 5}, KeyEvent.VK_P),
    Q(new int[] {1, 2, 3, 4, 5}, KeyEvent.VK_Q),
    R(new int[] {1, 3, 4, 5}, KeyEvent.VK_R),
    S(new int[] {2, 3, 5}, KeyEvent.VK_S),
    T(new int[] {2, 3, 4, 5}, KeyEvent.VK_T),
    U(new int[] {1, 5, 6}, KeyEvent.VK_U),
    V(new int[] {1, 3, 5, 6}, KeyEvent.VK_V),
    W(new int[] {2, 3, 4, 6}, KeyEvent.VK_W),
    X(new int[] {1, 2, 5, 6}, KeyEvent.VK_X),
    Y(new int[] {1, 2, 4, 5, 6}, KeyEvent.VK_Y),
    Z(new int[] {1, 4, 5, 6}, KeyEvent.VK_Z);

    int[] points;
    int key;

    Caractere(int[] pointsBraille, int key) {
        this.points = pointsBraille;
        this.key = key;
    }

    public int[] getPoints() {
        return points;
    }

    public int getKey() {
        return key;
    }
}
