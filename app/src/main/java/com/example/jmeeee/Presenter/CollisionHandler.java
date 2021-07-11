package com.example.jmeeee.Presenter;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class CollisionHandler {
    //判断两数是否相近
    public static boolean close(float a, float b, float precision) {
        return FastMath.abs(a - b) < precision ? true : false;
    }

    //判断碰撞点在哪个面
    public static char checkCollisionPoint(Vector3f point) {
        if (close(point.getX(),1.49f,0.003f)) {
            return 'R';//左面
        } else if (close(point.getX(),-1.49f,0.003f)) {
            return 'L';//右面
        } else if (close(point.getY(),1.49f,0.003f)) {
            return 'U';//顶面
        } else if (close(point.getY(),-1.49f,0.003f)) {
            return 'D';//底面
        } else if (close(point.getZ(),1.49f,0.003f)) {
            return 'F';//前面
        } else if (close(point.getZ(),-1.49f,0.003f)) {
            return 'B';//后面
        }
        return 'N';
    }

    //判断碰撞点在哪个块
    //返回结果如R_LU,就代表了右面的左上块
    public static String checkPointWhereSquare(Vector3f point, char whichFace) {
        String result = String.valueOf(whichFace);
        switch (whichFace) {
            case 'R':
                if (close(point.getY(),1,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_LU";
                }else if(close(point.getY(),0,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_L";
                }else if(close(point.getY(),-1,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_LD";
                }else if(close(point.getY(),1,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_U";
                }else if(close(point.getY(),0,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_M";
                }else if(close(point.getY(),-1,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_D";
                }else if(close(point.getY(),1,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_RU";
                }else if(close(point.getY(),0,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_R";
                }else if(close(point.getY(),-1,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_RD";
                }
                    break;
            case 'L':
                if (close(point.getY(),1,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_LU";
                }else if(close(point.getY(),0,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_L";
                }else if(close(point.getY(),-1,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_LD";
                }else if(close(point.getY(),1,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_U";
                }else if(close(point.getY(),0,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_M";
                }else if(close(point.getY(),-1,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_D";
                }else if(close(point.getY(),1,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_RU";
                }else if(close(point.getY(),0,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_R";
                }else if(close(point.getY(),-1,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_RD";
                }
                break;
            case 'U':
                if (close(point.getX(),-1,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_LU";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_L";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_LD";
                }else if(close(point.getX(),0,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_U";
                }else if(close(point.getX(),0,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_M";
                }else if(close(point.getX(),0,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_D";
                }else if(close(point.getX(),1,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_RU";
                }else if(close(point.getX(),1,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_R";
                }else if(close(point.getX(),1,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_RD";
                }
                break;
            case 'D':
                if (close(point.getX(),-1,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_LU";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_L";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_LD";
                }else if(close(point.getX(),0,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_U";
                }else if(close(point.getX(),0,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_M";
                }else if(close(point.getX(),0,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_D";
                }else if(close(point.getX(),1,0.49f)&&close(point.getZ(),1,0.49f))
                {
                    result+="_RU";
                }else if(close(point.getX(),1,0.49f)&&close(point.getZ(),0,0.49f))
                {
                    result+="_R";
                }else if(close(point.getX(),1,0.49f)&&close(point.getZ(),-1,0.49f))
                {
                    result+="_RD";
                }
                break;
            case 'F':
                if (close(point.getX(),-1,0.49f)&&close(point.getY(),1,0.49f))
                {
                    result+="_LU";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getY(),0,0.49f))
                {
                    result+="_L";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getY(),-1,0.49f))
                {
                    result+="_LD";
                }else if(close(point.getX(),0,0.49f)&&close(point.getY(),1,0.49f))
                {
                    result+="_U";
                }else if(close(point.getX(),0,0.49f)&&close(point.getY(),0,0.49f))
                {
                    result+="_M";
                }else if(close(point.getX(),0,0.49f)&&close(point.getY(),-1,0.49f))
                {
                    result+="_D";
                }else if(close(point.getX(),1,0.49f)&&close(point.getY(),1,0.49f))
                {
                    result+="_RU";
                }else if(close(point.getX(),1,0.49f)&&close(point.getY(),0,0.49f))
                {
                    result+="_R";
                }else if(close(point.getX(),1,0.49f)&&close(point.getY(),-1,0.49f))
                {
                    result+="_RD";
                }
                break;
            case 'B':
                if (close(point.getX(),1,0.49f)&&close(point.getY(),1,0.49f))
                {
                    result+="_LU";
                }else if(close(point.getX(),1,0.49f)&&close(point.getY(),0,0.49f))
                {
                    result+="_L";
                }else if(close(point.getX(),1,0.49f)&&close(point.getY(),-1,0.49f))
                {
                    result+="_LD";
                }else if(close(point.getX(),0,0.49f)&&close(point.getY(),1,0.49f))
                {
                    result+="_U";
                }else if(close(point.getX(),0,0.49f)&&close(point.getY(),0,0.49f))
                {
                    result+="_M";
                }else if(close(point.getX(),0,0.49f)&&close(point.getY(),-1,0.49f))
                {
                    result+="_D";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getY(),1,0.49f))
                {
                    result+="_RU";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getY(),0,0.49f))
                {
                    result+="_R";
                }else if(close(point.getX(),-1,0.49f)&&close(point.getY(),-1,0.49f))
                {
                    result+="_RD";
                }
                break;
            default:
                break;
        }
        return result;
    }


}
