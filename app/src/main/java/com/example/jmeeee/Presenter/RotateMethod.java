package com.example.jmeeee.Presenter;

import android.util.Log;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import static android.content.ContentValues.TAG;

public class RotateMethod {

    //执行公式U的旋转
    public static void rotate_U() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][2][j].getLocalRotation();//获得当前的旋转状态
                Vector3f yAxis = q.inverse().mult(Vector3f.UNIT_Y);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, yAxis);
                ParaHandler.Rcube[i][2][j].rotate(rot);
            }
        }

//        Node[] nodes = getUp();
//        for(int i=0;i<nodes.length;i++)
//        {
//            Quaternion q = nodes[i].getLocalRotation();//获得当前的旋转状态
//            Vector3f yAxis = q.inverse().mult(Vector3f.UNIT_Y);//计算一个新的旋转轴
//            Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, yAxis);
//            nodes[i].rotate(rot);
//        }
    }

    //执行公式U'的旋转
    public static void rotate_u() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][2][j].getLocalRotation();//获得当前的旋转状态
                Vector3f yAxis = q.inverse().mult(Vector3f.UNIT_Y);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, yAxis);
                ParaHandler.Rcube[i][2][j].rotate(rot);
            }
        }
    }


    //执行公式R'的旋转
    public static void rotate_r() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[2][i][j].getLocalRotation();
                Vector3f xAxis = q.inverse().mult(Vector3f.UNIT_X);
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, xAxis);
                ParaHandler.Rcube[2][i][j].rotate(rot);
            }
        }
//        Node[] nodes = getRight();
//        Log.i(TAG, "rotate_r: -------count:" +nodes.length);
//        for(int i=0;i<nodes.length;i++)
//        {
//            Quaternion q = nodes[i].getLocalRotation();//获得当前的旋转状态
//            Vector3f xAxis = q.inverse().mult(Vector3f.UNIT_X);//计算一个新的旋转轴
//            Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, xAxis);
//            nodes[i].rotate(rot);
//        }
    }

    //执行公式R的旋转
    public static void rotate_R() {
        //向后转
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[2][i][j].getLocalRotation();//获取当前旋转状态
                Vector3f xAxis = q.inverse().mult(Vector3f.UNIT_X);//计算新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, xAxis);//初始化旋转参数四元数
                ParaHandler.Rcube[2][i][j].rotate(rot);//旋转
            }
        }

    }

    //执行公式L'的旋转
    public static void rotate_l() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[0][i][j].getLocalRotation();
                Vector3f xAxis = q.inverse().mult(Vector3f.UNIT_X);
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, xAxis);
                ParaHandler.Rcube[0][i][j].rotate(rot);
            }
        }
    }

    //执行公式L的旋转
    public static void rotate_L() {
        //向后转
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[0][i][j].getLocalRotation();
                Vector3f xAxis = q.inverse().mult(Vector3f.UNIT_X);
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, xAxis);
                ParaHandler.Rcube[0][i][j].rotate(rot);
            }
        }

    }

    //执行公式D的旋转
    public static void rotate_D() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][0][j].getLocalRotation();//获得当前的旋转状态
                Vector3f yAxis = q.inverse().mult(Vector3f.UNIT_Y);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, yAxis);
                ParaHandler.Rcube[i][0][j].rotate(rot);
            }
        }
    }

    //执行公式D'的旋转
    public static void rotate_d() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][0][j].getLocalRotation();//获得当前的旋转状态
                Vector3f yAxis = q.inverse().mult(Vector3f.UNIT_Y);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, yAxis);
                ParaHandler.Rcube[i][0][j].rotate(rot);
            }
        }
    }

    //执行公式F的旋转
    public static void rotate_F() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][j][2].getLocalRotation();//获得当前的旋转状态
                Vector3f zAxis = q.inverse().mult(Vector3f.UNIT_Z);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, zAxis);
                ParaHandler.Rcube[i][j][2].rotate(rot);
            }
        }
    }

    //执行公式f的旋转
    public static void rotate_f() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][j][2].getLocalRotation();//获得当前的旋转状态
                Vector3f zAxis = q.inverse().mult(Vector3f.UNIT_Z);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, zAxis);
                ParaHandler.Rcube[i][j][2].rotate(rot);
            }
        }
    }

    //执行公式B的旋转
    public static void rotate_B() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][j][0].getLocalRotation();//获得当前的旋转状态
                Vector3f zAxis = q.inverse().mult(Vector3f.UNIT_Z);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, zAxis);
                ParaHandler.Rcube[i][j][0].rotate(rot);
            }
        }
    }

    //执行公式B'的旋转
    public static void rotate_b() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][j][0].getLocalRotation();//获得当前的旋转状态
                Vector3f zAxis = q.inverse().mult(Vector3f.UNIT_Z);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, zAxis);
                ParaHandler.Rcube[i][j][0].rotate(rot);
            }
        }
    }

    //执行公式Z的旋转
    public static void rotate_Z() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][j][1].getLocalRotation();//获得当前的旋转状态
                Vector3f zAxis = q.inverse().mult(Vector3f.UNIT_Z);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, zAxis);
                ParaHandler.Rcube[i][j][1].rotate(rot);
            }
        }
    }

    //执行公式z的旋转
    public static void rotate_z() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][j][1].getLocalRotation();//获得当前的旋转状态
                Vector3f zAxis = q.inverse().mult(Vector3f.UNIT_Z);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, zAxis);
                ParaHandler.Rcube[i][j][1].rotate(rot);
            }
        }
    }

    //执行公式x的旋转
    public static void rotate_x() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[1][i][j].getLocalRotation();
                Vector3f xAxis = q.inverse().mult(Vector3f.UNIT_X);
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, xAxis);
                ParaHandler.Rcube[1][i][j].rotate(rot);
            }
        }
    }

    //执行公式X的旋转
    public static void rotate_X() {
        //向后转
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[1][i][j].getLocalRotation();
                Vector3f xAxis = q.inverse().mult(Vector3f.UNIT_X);
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, xAxis);
                ParaHandler.Rcube[1][i][j].rotate(rot);
            }
        }

    }
    //执行公式Y的旋转
    public static void rotate_Y() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][1][j].getLocalRotation();//获得当前的旋转状态
                Vector3f yAxis = q.inverse().mult(Vector3f.UNIT_Y);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(-FastMath.HALF_PI / 30, yAxis);
                ParaHandler.Rcube[i][1][j].rotate(rot);
            }
        }

    }

    //执行公式y的旋转
    public static void rotate_y() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Quaternion q = ParaHandler.Rcube[i][1][j].getLocalRotation();//获得当前的旋转状态
                Vector3f yAxis = q.inverse().mult(Vector3f.UNIT_Y);//计算一个新的旋转轴
                Quaternion rot = new Quaternion().fromAngleAxis(FastMath.HALF_PI / 30, yAxis);
                ParaHandler.Rcube[i][1][j].rotate(rot);
            }
        }
    }

    //供主类调用的旋转函数
    public static void rotate(char formula) {
        switch (formula) {
            case 'R':
                rotate_R();
                break;
            case 'r':
                rotate_r();
                break;
            case 'L':
                rotate_L();
                break;
            case 'l':
                rotate_l();
                break;
            case 'U':
                rotate_U();
                break;
            case 'u':
                rotate_u();
                break;
            case 'D':
                rotate_D();
                break;
            case 'd':
                rotate_d();
                break;
            case 'F':
                rotate_F();
                break;
            case 'f':
                rotate_f();
                break;
            case 'B':
                rotate_B();
                break;
            case 'b':
                rotate_b();
                break;
            case 'X':
                rotate_X();
                break;
            case 'x':
                rotate_x();
                break;
            case 'Y':
                rotate_Y();
                break;
            case 'y':
                rotate_y();
                break;
            case 'Z':
                rotate_Z();
                break;
            case 'z':
                rotate_z();
                break;
            default:
                break;
        }
    }

    //供主类调用的旋转数组函数
    public static void rotateArray(char formula) {
        switch (formula) {
            case 'R':
                rotateArray_R();
                ParaHandler.temp_state+="R";
                break;
            case 'r':
                rotateArray_r();
                ParaHandler.temp_state+="r";
                break;
            case 'L':
                rotateArray_L();
                ParaHandler.temp_state+="L";
                break;
            case 'l':
                rotateArray_l();
                ParaHandler.temp_state+="l";
                break;
            case 'U':
                rotateArray_U();
                ParaHandler.temp_state+="U";
                break;
            case 'u':
                rotateArray_u();
                ParaHandler.temp_state+="u";
                break;
            case 'D':
                rotateArray_D();
                ParaHandler.temp_state+="D";
                break;
            case 'd':
                rotateArray_d();
                ParaHandler.temp_state+="d";
                break;
            case 'F':
                rotateArray_F();
                ParaHandler.temp_state+="F";
                break;
            case 'f':
                rotateArray_f();
                ParaHandler.temp_state+="f";
                break;
            case 'B':
                rotateArray_B();
                ParaHandler.temp_state+="B";
                break;
            case 'b':
                rotateArray_b();
                ParaHandler.temp_state+="b";
                break;
            case 'X':
                rotateArray_X();
                ParaHandler.temp_state+="X";
                break;
            case 'x':
                rotateArray_x();
                ParaHandler.temp_state+="x";
                break;
            case 'Y':
                rotateArray_Y();
                ParaHandler.temp_state+="Y";
                break;
            case 'y':
                rotateArray_y();
                ParaHandler.temp_state+="y";
                break;
            case 'Z':
                rotateArray_Z();
                ParaHandler.temp_state+="Z";
                break;
            case 'z':
                rotateArray_z();
                ParaHandler.temp_state+="z";
                break;
            default:
                break;
        }
    }

    //求相反公式
    public static char inverse_formula(char a) {
        switch (a) {
            case 'R':
                return 'r';
            case 'r':
                return 'R';
            case 'L':
                return 'l';
            case 'l':
                return 'L';
            case 'U':
                return 'u';
            case 'u':
                return 'U';
            case 'D':
                return 'd';
            case 'd':
                return 'D';
            case 'F':
                return 'f';
            case 'f':
                return 'F';
            case 'B':
                return 'b';
            case 'b':
                return 'B';
            case 'X':
                return 'x';
            case 'x':
                return 'X';
            case 'Y':
                return 'y';
            case 'y':
                return 'Y';
            case 'Z':
                return 'z';
            case 'z':
                return 'Z';
            default:
                break;
        }
        return 'N';
    }


//    public static Node[] getUp()
//    {
//        Node[] nodes = new Node[9];
//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    Vector3f curloc = ParaHandler.Rcube[i][j][k].getWorldRotation().inverse().mult(new Vector3f(i-1,j-1,k-1));
//                    if(CollisionHandler.close(curloc.getY(),1.0f,0.1f))
//                    {
//                        nodes[count++] = ParaHandler.Rcube[i][j][k];
//                        Log.i(TAG, "getUp: --------->"+ParaHandler.Rcube[i][j][k].getLocalTranslation().toString());
//                    }
//                }
//            }
//        }
//        return nodes;
//    }
//
//    public static Node[] getRight()
//    {
//        Node[] nodes = new Node[9];
//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    Vector3f curloc = ParaHandler.Rcube[i][j][k].getWorldRotation().inverse().mult(new Vector3f(i-1,j-1,k-1));
//                    if(CollisionHandler.close(curloc.getX(),1.0f,0.1f))
//                    {
//                        nodes[count++] = ParaHandler.Rcube[i][j][k];
//                    }
//                }
//            }
//        }
//        return nodes;
//    }

    //执行公式R之后对数组进行的操作
    public static void rotateArray_R() {
        swapNode(new int[]{2, 1, 0}, new int[]{2, 0, 1});
        swapNode(new int[]{2, 1, 0}, new int[]{2, 2, 1});
        swapNode(new int[]{2, 1, 2}, new int[]{2, 2, 1});

        swapNode(new int[]{2, 0, 0}, new int[]{2, 0, 2});
        swapNode(new int[]{2, 0, 0}, new int[]{2, 2, 0});
        swapNode(new int[]{2, 2, 2}, new int[]{2, 2, 0});
    }

    //执行公式R'之后对数组进行的操作
    public static void rotateArray_r() {
        swapNode(new int[]{2, 0, 1}, new int[]{2, 1, 0});
        swapNode(new int[]{2, 0, 1}, new int[]{2, 1, 2});
        swapNode(new int[]{2, 2, 1}, new int[]{2, 1, 2});

        swapNode(new int[]{2, 0, 2}, new int[]{2, 0, 0});
        swapNode(new int[]{2, 0, 2}, new int[]{2, 2, 2});
        swapNode(new int[]{2, 2, 0}, new int[]{2, 2, 2});
    }

    //执行公式U之后对数组进行的操作
    public static void rotateArray_U() {
        swapNode(new int[]{2, 2, 1}, new int[]{1, 2, 2});
        swapNode(new int[]{2, 2, 1}, new int[]{1, 2, 0});
        swapNode(new int[]{0, 2, 1}, new int[]{1, 2, 0});

        swapNode(new int[]{2, 2, 0}, new int[]{2, 2, 2});
        swapNode(new int[]{2, 2, 0}, new int[]{0, 2, 0});
        swapNode(new int[]{0, 2, 2}, new int[]{0, 2, 0});
    }

    //执行公式U'之后对数组进行的操作
    public static void rotateArray_u() {
        swapNode(new int[]{0, 2, 1}, new int[]{1, 2, 2});
        swapNode(new int[]{0, 2, 1}, new int[]{1, 2, 0});
        swapNode(new int[]{2, 2, 1}, new int[]{1, 2, 0});

        swapNode(new int[]{0, 2, 2}, new int[]{2, 2, 2});
        swapNode(new int[]{0, 2, 2}, new int[]{0, 2, 0});
        swapNode(new int[]{2, 2, 0}, new int[]{0, 2, 0});
    }

    //执行公式D之后对数组进行的操作
    public static void rotateArray_d() {
        swapNode(new int[]{1, 0, 2}, new int[]{0, 0, 1});
        swapNode(new int[]{1, 0, 2}, new int[]{2, 0, 1});
        swapNode(new int[]{1, 0, 0}, new int[]{2, 0, 1});

        swapNode(new int[]{2, 0, 2}, new int[]{0, 0, 2});
        swapNode(new int[]{2, 0, 2}, new int[]{2, 0, 0});
        swapNode(new int[]{0, 0, 0}, new int[]{2, 0, 0});
    }

    //执行公式D'之后对数组进行的操作
    public static void rotateArray_D() {
        swapNode(new int[]{1, 0, 2}, new int[]{2, 0, 1});
        swapNode(new int[]{1, 0, 2}, new int[]{0, 0, 1});
        swapNode(new int[]{1, 0, 0}, new int[]{0, 0, 1});

        swapNode(new int[]{2, 0, 2}, new int[]{2, 0, 0});
        swapNode(new int[]{2, 0, 2}, new int[]{0, 0, 2});
        swapNode(new int[]{0, 0, 0}, new int[]{0, 0, 2});
    }

    //执行公式L之后对数组进行的操作
    public static void rotateArray_L() {
        swapNode(new int[]{0, 1, 2}, new int[]{0, 0, 1});
        swapNode(new int[]{0, 1, 2}, new int[]{0, 2, 1});
        swapNode(new int[]{0, 1, 0}, new int[]{0, 2, 1});

        swapNode(new int[]{0, 2, 2}, new int[]{0, 0, 2});
        swapNode(new int[]{0, 2, 2}, new int[]{0, 2, 0});
        swapNode(new int[]{0, 0, 0}, new int[]{0, 2, 0});
    }

    //执行公式L'之后对数组进行的操作
    public static void rotateArray_l() {
        swapNode(new int[]{0, 1, 2}, new int[]{0, 2, 1});
        swapNode(new int[]{0, 1, 2}, new int[]{0, 0, 1});
        swapNode(new int[]{0, 1, 0}, new int[]{0, 0, 1});

        swapNode(new int[]{0, 2, 2}, new int[]{0, 2, 0});
        swapNode(new int[]{0, 2, 2}, new int[]{0, 0, 2});
        swapNode(new int[]{0, 0, 0}, new int[]{0, 0, 2});
    }

    //执行公式F之后对数组进行的操作
    public static void rotateArray_F() {
        swapNode(new int[]{1, 2, 2}, new int[]{2, 1, 2});
        swapNode(new int[]{1, 2, 2}, new int[]{0, 1, 2});
        swapNode(new int[]{1, 0, 2}, new int[]{0, 1, 2});

        swapNode(new int[]{2, 2, 2}, new int[]{2, 0, 2});
        swapNode(new int[]{2, 2, 2}, new int[]{0, 2, 2});
        swapNode(new int[]{0, 0, 2}, new int[]{0, 2, 2});
    }

    //执行公式F'之后对数组进行的操作
    public static void rotateArray_f() {
        swapNode(new int[]{1, 2, 2}, new int[]{0, 1, 2});
        swapNode(new int[]{1, 2, 2}, new int[]{2, 1, 2});
        swapNode(new int[]{1, 0, 2}, new int[]{2, 1, 2});

        swapNode(new int[]{2, 2, 2}, new int[]{0, 2, 2});
        swapNode(new int[]{2, 2, 2}, new int[]{2, 0, 2});
        swapNode(new int[]{0, 0, 2}, new int[]{2, 0, 2});
    }

    //执行公式B之后对数组进行的操作
    public static void rotateArray_B() {
        swapNode(new int[]{1, 2, 0}, new int[]{0, 1, 0});
        swapNode(new int[]{1, 2, 0}, new int[]{2, 1, 0});
        swapNode(new int[]{1, 0, 0}, new int[]{2, 1, 0});

        swapNode(new int[]{2, 2, 0}, new int[]{0, 2, 0});
        swapNode(new int[]{2, 2, 0}, new int[]{2, 0, 0});
        swapNode(new int[]{0, 0, 0}, new int[]{2, 0, 0});
    }

    //执行公式B'之后对数组进行的操作
    public static void rotateArray_b() {
        swapNode(new int[]{1, 2, 0}, new int[]{2, 1, 0});
        swapNode(new int[]{1, 2, 0}, new int[]{0, 1, 0});
        swapNode(new int[]{1, 0, 0}, new int[]{0, 1, 0});

        swapNode(new int[]{2, 2, 0}, new int[]{2, 0, 0});
        swapNode(new int[]{2, 2, 0}, new int[]{0, 2, 0});
        swapNode(new int[]{0, 0, 0}, new int[]{0, 2, 0});
    }

    //执行公式X之后对数组进行的操作
    public static void rotateArray_X() {
        swapNode(new int[]{1, 1, 0}, new int[]{1, 0, 1});
        swapNode(new int[]{1, 1, 0}, new int[]{1, 2, 1});
        swapNode(new int[]{1, 1, 2}, new int[]{1, 2, 1});

        swapNode(new int[]{1, 0, 0}, new int[]{1, 0, 2});
        swapNode(new int[]{1, 0, 0}, new int[]{1, 2, 0});
        swapNode(new int[]{1, 2, 2}, new int[]{1, 2, 0});
    }

    //执行公式x之后对数组进行的操作
    public static void rotateArray_x() {
        swapNode(new int[]{1, 0, 1}, new int[]{1, 1, 0});
        swapNode(new int[]{1, 0, 1}, new int[]{1, 1, 2});
        swapNode(new int[]{1, 2, 1}, new int[]{1, 1, 2});

        swapNode(new int[]{1, 0, 2}, new int[]{1, 0, 0});
        swapNode(new int[]{1, 0, 2}, new int[]{1, 2, 2});
        swapNode(new int[]{1, 2, 0}, new int[]{1, 2, 2});
    }
    //执行公式Z之后对数组进行的操作
    public static void rotateArray_Z() {
        swapNode(new int[]{1, 2, 1}, new int[]{0, 1, 1});
        swapNode(new int[]{1, 2, 1}, new int[]{2, 1, 1});
        swapNode(new int[]{1, 0, 1}, new int[]{2, 1, 1});

        swapNode(new int[]{2, 2, 1}, new int[]{0, 2, 1});
        swapNode(new int[]{2, 2, 1}, new int[]{2, 0, 1});
        swapNode(new int[]{0, 0, 1}, new int[]{2, 0, 1});
    }

    //执行公式z之后对数组进行的操作
    public static void rotateArray_z() {
        swapNode(new int[]{1, 2, 1}, new int[]{2, 1, 1});
        swapNode(new int[]{1, 2, 1}, new int[]{0, 1, 1});
        swapNode(new int[]{1, 0, 1}, new int[]{0, 1, 1});

        swapNode(new int[]{2, 2, 1}, new int[]{2, 0, 1});
        swapNode(new int[]{2, 2, 1}, new int[]{0, 2, 1});
        swapNode(new int[]{0, 0, 1}, new int[]{0, 2, 1});
    }
    //执行公式Y之后对数组进行的操作
    public static void rotateArray_Y() {
        swapNode(new int[]{2, 1, 1}, new int[]{1, 1, 2});
        swapNode(new int[]{2, 1, 1}, new int[]{1, 1, 0});
        swapNode(new int[]{0, 1, 1}, new int[]{1, 1, 0});

        swapNode(new int[]{2, 1, 0}, new int[]{2, 1, 2});
        swapNode(new int[]{2, 1, 0}, new int[]{0, 1, 0});
        swapNode(new int[]{0, 1, 2}, new int[]{0, 1, 0});
    }

    //执行公式y之后对数组进行的操作
    public static void rotateArray_y() {
        swapNode(new int[]{0, 1, 1}, new int[]{1, 1, 2});
        swapNode(new int[]{0, 1, 1}, new int[]{1, 1, 0});
        swapNode(new int[]{2, 1, 1}, new int[]{1, 1, 0});

        swapNode(new int[]{0, 1, 2}, new int[]{2, 1, 2});
        swapNode(new int[]{0, 1, 2}, new int[]{0, 1, 0});
        swapNode(new int[]{2, 1, 0}, new int[]{0, 1, 0});
    }
    //交换两个节点
    public static void swapNode(int[] a, int[] b) {
        Node tempnode = ParaHandler.Rcube[a[0]][a[1]][a[2]];
        ParaHandler.Rcube[a[0]][a[1]][a[2]] = ParaHandler.Rcube[b[0]][b[1]][b[2]];
        ParaHandler.Rcube[b[0]][b[1]][b[2]] = tempnode;
    }
}


