package com.example.jmeeee.Presenter;


import android.util.Log;

import com.example.jmeeee.Model.DBHelper;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

import java.util.List;

import static android.content.ContentValues.TAG;


public class HelloRubik extends SimpleApplication {


    ChaseCamera chaseCamera;
    Ray ray;
    Vector3f up = new Vector3f();
    Vector3f down = new Vector3f();
    float rotate_count = 0, temp_distance = 0;
    char current_formula = ' ';
    private boolean rotate_unlock = true;

    @Override
    public void simpleInitApp() {

        //初始化射线
        ray = new Ray();
        //取消显示参数
        this.setDisplayFps(false);
        this.setDisplayStatView(false);
        rootNode.setLocalTranslation(0, 0, 0);

        //添加监听器
        inputManager.addRawInputListener(new MyRawInputListener());
        //建立魔方
        int cubenum[] = {0, 0, 0};
        char[] color = new char[6];
        char[][][][] colors = new char[3][3][3][6];

        //颜色表
        String colortxt = "0@0@0@0GO00W\n" + "0@0@1@00O00W\n" + "0@0@2@B0O00W\n" +
                "0@1@0@0GO000\n" + "0@1@1@00O000\n" + "0@1@2@B0O000\n" +
                "0@2@0@0GO0Y0\n" + "0@2@1@00O0Y0\n" + "0@2@2@B0O0Y0\n" +
                "1@0@0@0G000W\n" + "1@0@1@00000W\n" + "1@0@2@B0000W\n" +
                "1@1@0@0G0000\n" + "1@1@1@000000\n" + "1@1@2@B00000\n" +
                "1@2@0@0G00Y0\n" + "1@2@1@0000Y0\n" + "1@2@2@B000Y0\n" +
                "2@0@0@0G0R0W\n" + "2@0@1@000R0W\n" + "2@0@2@B00R0W\n" +
                "2@1@0@0G0R00\n" + "2@1@1@000R00\n" + "2@1@2@B00R00\n" +
                "2@2@0@0G0RY0\n" + "2@2@1@000RY0\n" + "2@2@2@B00RY0";

        //建立魔方
        int n = 0;
        String[] strcolor = colortxt.split("\\n");
        String tempString = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    tempString = strcolor[n];
                    n++;
                    String[] temoString = tempString.split("@");
                    for (int m = 0; m < 6; m++) {
                        color[m] = temoString[3].charAt(m);
                    }
                    colors[i][j][k] = color;
                    ParaHandler.Rcube[i][j][k] = createCube(0.49f, new Vector3f(i - 1, j - 1, k - 1), colors[i][j][k]);
                    rootNode.attachChild(ParaHandler.Rcube[i][j][k]);
                }
            }
        }
        //恢复存档状态
        if(ParaHandler.isIsfromloadfile())
        {
            String states = ParaHandler.read_state;
            if(states.length()>1)
            {
               for(int i=0;i<states.length();i++)
               {
                   for(int j=0;j<30;j++)
                   {
                       RotateMethod.rotate(states.charAt(i));
                   }
                   RotateMethod.rotateArray(states.charAt(i));
               }
            }
            ParaHandler.temp_state = "";
        }


        // 创建一束定向光，并让它斜向下照射。
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));
        DirectionalLight sun2 = new DirectionalLight();
        sun2.setDirection(new Vector3f(1, 2, 3));
        // 将光源都添加到场景图
        rootNode.addLight(sun);
        rootNode.addLight(sun2);

        //新建一个跟随的摄像机
        chaseCamera = new ChaseCamera(cam, rootNode, inputManager);
        chaseCamera.setInvertVerticalAxis(false);//设置相机其是否反转垂直坐标
        chaseCamera.setMaxVerticalRotation(FastMath.PI * 2);//设置相机最大的垂直旋转角度
        chaseCamera.setDefaultDistance(12f);//设置相机与目标物体的距离
        chaseCamera.setDefaultHorizontalRotation(-(FastMath.HALF_PI / 2) * 3);//设置相机默认的水平旋转角度
        chaseCamera.setDefaultVerticalRotation(FastMath.PI - FastMath.HALF_PI / 4);//设置相机默认的垂直旋转角度
        chaseCamera.setRotationSpeed(1.8f);
    }

    /**
     * 主循环
     */
    @Override
    public void simpleUpdate(float deltaTime) {
        //打乱魔方
        if (ParaHandler.getIsroll()) {
            RollCube.rollcube();
            ParaHandler.setIsroll(false);
        }
    }

    /**
     * 创建一个方块
     * color数组包含六个元素，依次代表0前1后2左3右4上5下的颜色
     * 颜色红R，蓝B，绿G，黄Y，白W，橙O
     */
    private Node createCube(float a, Vector3f vecloa, char color[]) {
        Node CubeNode = new Node();//建立子块节点
        //建立子块的立方体
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");//新建一个材质
        Geometry geometry = new Geometry("Box", new Box(a, a, a));//新建一个几何体，并添加上Box的网格
        geometry.setMaterial(mat);//设置立方体的材质
        geometry.setLocalTranslation(vecloa);//设置立方体的位置
        CubeNode.attachChild(geometry);//将子块的立方体连接到子块节点
        for (int i = 0; i < 6; i++) {
            if (color[i] == '0') {
                continue;
            } else {
                Material matsurf = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                Vector3f vecsurf = new Vector3f();
                Geometry geom = new Geometry("Box");
                float[] size = {a - 0.01f, a - 0.01f, a - 0.01f};
                switch (i) {//判断该色片位于那一面，然后计算它的位置
                    case 0:
                        size[2] = 0.001f;
                        vecsurf.set(vecloa.x, vecloa.y, vecloa.z + a);
                        break;
                    case 1:
                        size[2] = 0.001f;
                        vecsurf.set(vecloa.x, vecloa.y, vecloa.z - a);
                        break;
                    case 2:
                        size[0] = 0.001f;
                        vecsurf.set(vecloa.x - a, vecloa.y, vecloa.z);
                        break;
                    case 3:
                        size[0] = 0.001f;
                        vecsurf.set(vecloa.x + a, vecloa.y, vecloa.z);
                        break;
                    case 4:
                        size[1] = 0.001f;
                        vecsurf.set(vecloa.x, vecloa.y + a, vecloa.z);
                        break;
                    case 5:
                        size[1] = 0.001f;
                        vecsurf.set(vecloa.x, vecloa.y - a, vecloa.z);
                        break;
                    default:
                        break;
                }
                switch (color[i]) {//判断该色片的颜色，然后给材质设置对应的颜色
                    case 'R':
                        matsurf.setColor("Color", ColorRGBA.Red);
                        break;
                    case 'B':
                        matsurf.setColor("Color", ColorRGBA.Blue);
                        break;
                    case 'G':
                        matsurf.setColor("Color", ColorRGBA.Green);
                        break;
                    case 'Y':
                        matsurf.setColor("Color", ColorRGBA.Yellow);
                        break;
                    case 'W':
                        matsurf.setColor("Color", ColorRGBA.White);
                        break;
                    case 'O':
                        matsurf.setColor("Color", ColorRGBA.Orange);
                        break;
                    default:
                        break;
                }
                geom.setMesh(new Box(size[0], size[1], size[2]));//设置色片的网格
                geom.setMaterial(matsurf);//设置色片的材质
                geom.setLocalTranslation(vecsurf);//设置色片的位置
                CubeNode.setLocalTranslation(0, 0, 0);
                CubeNode.attachChild(geom);//将该色片连接至子块节点
            }
        }

        CubeNode.setLocalTranslation(0, 0, 0);
//        Node xnode= new Node();
//        xnode.setName("Xnode");
//        xnode.setLocalTranslation(0,0,0);
//        xnode.attachChild(CubeNode);
//        Node ynode = new Node();
//        ynode.setLocalTranslation(0,0,0);
//        ynode.setName("Ynode");
//        ynode.attachChild(CubeNode);
//        Node znode = new Node();
//        znode.setLocalTranslation(0,0,0);
//        znode.setName("Znode");
//        znode.attachChild(CubeNode);
//        Node cube_root = new Node();
//        cube_root.attachChild(xnode);
//        cube_root.attachChild(ynode);
//        cube_root.attachChild(znode);
        return CubeNode;
    }

    //更新射线
    private Ray updateRay() {
        ray.setOrigin(cam.getLocation());
        Vector2f screenCoord = inputManager.getCursorPosition();//获取屏幕上的二维坐标
        Vector3f worldCoord = cam.getWorldCoordinates(screenCoord, 1f);//将二维坐标转换为三维坐标
        // 计算方向
        Vector3f dir = worldCoord.subtract(cam.getLocation());
        dir.normalizeLocal();//将方向向量标准化
        ray.setDirection(dir);
        return ray;
    }

    //拾取最近的点
    private Vector3f pick() {
        Ray ray = updateRay();
        Vector3f position = new Vector3f();
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);// 碰撞检测
        if (results.size() > 0) {
            // 放置拾取标记
            position = results.getClosestCollision().getContactPoint();
        }
        return position;
    }

    //事件监听器
    class MyRawInputListener implements RawInputListener {


        @Override
        public void onMouseMotionEvent(MouseMotionEvent evt) {

        }

        @Override
        public void beginInput() {

        }

        @Override
        public void endInput() {

        }

        @Override
        public void onJoyAxisEvent(JoyAxisEvent joyAxisEvent) {

        }

        @Override
        public void onJoyButtonEvent(JoyButtonEvent joyButtonEvent) {

        }

        @Override
        public void onMouseButtonEvent(MouseButtonEvent mouseButtonEvent) {

        }

        @Override
        public void onKeyEvent(KeyInputEvent keyInputEvent) {

        }

        @Override
        public void onTouchEvent(TouchEvent touchEvent) {
            if (!ParaHandler.getIsmouseball()) {
                chaseCamera.setDragToRotate(true);
                if (touchEvent.getType() == TouchEvent.Type.DOWN) {//点击屏幕
                    down = pick();//获取点击坐标
                } else if (touchEvent.getType() == TouchEvent.Type.UP) {//抬起手指
                    if (rotate_count > 15) {//旋转角度过半
                        Log.i(TAG, "onTouchEvent: --->补上");
                        for (int i = 0; i < 30 - rotate_count; i++) {
                            RotateMethod.rotate(current_formula);
                        }
                        RotateMethod.rotateArray(current_formula);
                    } else {//旋转角度未过半
                        Log.i(TAG, "onTouchEvent: --->回滚" + rotate_count);
                        for (int i = 0; i < rotate_count; i++) {
                            RotateMethod.rotate(RotateMethod.inverse_formula(current_formula));//执行相反公式
                        }
                    }
                    rotate_count = 0;//重置旋转控制数
                    rotate_unlock = true;//重置旋转锁
                    current_formula = ' ';//重置当前公式
                } else if (touchEvent.getType() == TouchEvent.Type.MOVE) {
                    Vector3f temp = pick();
                    Vector3f directvec = temp.subtract(down);
                    //Log.i(TAG, "onTouchEvent: ----------->direcrvec:"+directvec.toString());
                    if (rotate_unlock) {
                        Log.i(TAG, "onTouchEvent: ------->whichface:" + CollisionHandler.checkCollisionPoint(down));
                        switch (CollisionHandler.checkCollisionPoint(down)) {
                            case 'R':
                                String whichSquareR = CollisionHandler.checkPointWhereSquare(down, 'R').split("_")[1];
                                Log.i(TAG, "onTouchEvent: --------->whichsqure:" + whichSquareR);
                                if (whichSquareR.equals("LU") || whichSquareR.equals("U") || whichSquareR.equals("RU")) {
                                    //顶层
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'U';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'u';
                                    }
                                }
                                if (whichSquareR.equals("LD") || whichSquareR.equals("D") || whichSquareR.equals("RD")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'd';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'D';
                                    }
                                }
                                if (whichSquareR.equals("LU") || whichSquareR.equals("L") || whichSquareR.equals("LD")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'f';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'F';
                                    }
                                }
                                if (whichSquareR.equals("RU") || whichSquareR.equals("R") || whichSquareR.equals("RD")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'B';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'b';
                                    }
                                }
                                if (whichSquareR.equals("L") || whichSquareR.equals("M") || whichSquareR.equals("R")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'Y';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'y';
                                    }
                                }
                                if (whichSquareR.equals("U") || whichSquareR.equals("M") || whichSquareR.equals("D")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'Z';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'z';
                                    }
                                }
                                break;
                            case 'L':
                                String whichSquareL = CollisionHandler.checkPointWhereSquare(down, 'L').split("_")[1];
                                Log.i(TAG, "onTouchEvent: --------->whichsqure:" + whichSquareL);
                                if (whichSquareL.equals("LU") || whichSquareL.equals("U") || whichSquareL.equals("RU")) {
                                    //顶层
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'u';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'U';
                                    }
                                }
                                if (whichSquareL.equals("LD") || whichSquareL.equals("D") || whichSquareL.equals("RD")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'D';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'd';
                                    }
                                }
                                if (whichSquareL.equals("LU") || whichSquareL.equals("L") || whichSquareL.equals("LD")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'b';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'B';
                                    }
                                }
                                if (whichSquareL.equals("RU") || whichSquareL.equals("R") || whichSquareL.equals("RD")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'F';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'f';
                                    }
                                }
                                if (whichSquareL.equals("L") || whichSquareL.equals("M") || whichSquareL.equals("R")) {

                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'y';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'Y';
                                    }
                                }
                                if (whichSquareL.equals("U") || whichSquareL.equals("M") || whichSquareL.equals("D")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'z';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'Z';
                                    }
                                }
                                break;
                            case 'U':
                                String whichSquareU = CollisionHandler.checkPointWhereSquare(down, 'U').split("_")[1];
                                Log.i(TAG, "onTouchEvent: --------->whichsqure:" + whichSquareU);
                                if (whichSquareU.equals("LU") || whichSquareU.equals("U") || whichSquareU.equals("RU")) {
                                    //顶层
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'b';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'B';
                                    }
                                }
                                if (whichSquareU.equals("LD") || whichSquareU.equals("D") || whichSquareU.equals("RD")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'F';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'f';
                                    }
                                }
                                if (whichSquareU.equals("LU") || whichSquareU.equals("L") || whichSquareU.equals("LD")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'L';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'l';
                                    }
                                }
                                if (whichSquareU.equals("RU") || whichSquareU.equals("R") || whichSquareU.equals("RD")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'r';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'R';
                                    }
                                }
                                if (whichSquareU.equals("U") || whichSquareU.equals("M") || whichSquareU.equals("D")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'x';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'X';
                                    }
                                }
                                if (whichSquareU.equals("L") || whichSquareU.equals("M") || whichSquareU.equals("R")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'z';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'Z';
                                    }
                                }
                                break;
                            case 'D':
                                String whichSquareD = CollisionHandler.checkPointWhereSquare(down, 'D').split("_")[1];
                                Log.i(TAG, "onTouchEvent: --------->whichsqure:" + whichSquareD);
                                if (whichSquareD.equals("LU") || whichSquareD.equals("U") || whichSquareD.equals("RU")) {
                                    //顶层
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'f';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'F';
                                    }
                                }
                                if (whichSquareD.equals("LD") || whichSquareD.equals("D") || whichSquareD.equals("RD")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'B';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'b';
                                    }
                                }
                                if (whichSquareD.equals("LU") || whichSquareD.equals("L") || whichSquareD.equals("LD")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'l';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'L';
                                    }
                                }
                                if (whichSquareD.equals("RU") || whichSquareD.equals("R") || whichSquareD.equals("RD")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'R';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'r';
                                    }
                                }
                                if (whichSquareD.equals("U") || whichSquareD.equals("M") || whichSquareD.equals("D")) {
                                    if (directvec.getZ() > 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'X';
                                    } else if (directvec.getZ() < 0 && FastMath.abs(directvec.getZ()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'x';
                                    }
                                }
                                if (whichSquareD.equals("L") || whichSquareD.equals("M") || whichSquareD.equals("R")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'Z';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getZ())) {
                                        current_formula = 'z';
                                    }
                                }
                                break;
                            case 'F':
                                String whichSquareF = CollisionHandler.checkPointWhereSquare(down, 'F').split("_")[1];
                                Log.i(TAG, "onTouchEvent: --------->whichsqure:" + whichSquareF);
                                if (whichSquareF.equals("LU") || whichSquareF.equals("U") || whichSquareF.equals("RU")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'u';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'U';
                                    }
                                }
                                if (whichSquareF.equals("LD") || whichSquareF.equals("D") || whichSquareF.equals("RD")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'D';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'd';
                                    }
                                }
                                if (whichSquareF.equals("LU") || whichSquareF.equals("L") || whichSquareF.equals("LD")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'l';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'L';
                                    }
                                }
                                if (whichSquareF.equals("RU") || whichSquareF.equals("R") || whichSquareF.equals("RD")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'R';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'r';
                                    }
                                }
                                if (whichSquareF.equals("L") || whichSquareF.equals("M") || whichSquareF.equals("R")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'y';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'Y';
                                    }
                                }
                                if (whichSquareF.equals("U") || whichSquareF.equals("M") || whichSquareF.equals("D")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'X';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'x';
                                    }
                                }
                                break;
                            case 'B':
                                String whichSquareB = CollisionHandler.checkPointWhereSquare(down, 'F').split("_")[1];
                                Log.i(TAG, "onTouchEvent: --------->whichsqure:" + whichSquareB);
                                if (whichSquareB.equals("LU") || whichSquareB.equals("U") || whichSquareB.equals("RU")) {
                                    //顶层
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'U';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'u';
                                    }
                                }
                                if (whichSquareB.equals("LD") || whichSquareB.equals("D") || whichSquareB.equals("RD")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'd';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'D';
                                    }
                                }
                                if (whichSquareB.equals("LU") || whichSquareB.equals("L") || whichSquareB.equals("LD")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'L';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'l';
                                    }
                                }
                                if (whichSquareB.equals("RU") || whichSquareB.equals("R") || whichSquareB.equals("RD")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'r';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'R';
                                    }
                                }
                                if (whichSquareB.equals("L") || whichSquareB.equals("M") || whichSquareB.equals("R")) {
                                    if (directvec.getX() > 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'Y';
                                    } else if (directvec.getX() < 0 && FastMath.abs(directvec.getX()) > FastMath.abs(directvec.getY())) {
                                        current_formula = 'y';
                                    }
                                }
                                if (whichSquareB.equals("U") || whichSquareB.equals("M") || whichSquareB.equals("D")) {
                                    if (directvec.getY() > 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'x';
                                    } else if (directvec.getY() < 0 && FastMath.abs(directvec.getY()) > FastMath.abs(directvec.getX())) {
                                        current_formula = 'X';
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                        Log.i(TAG, "onTouchEvent: ---------->currentformula:" + current_formula);
                        rotate_unlock = false;
                    }
                    float distance = directvec.length();
                    temp_distance = distance - rotate_count / 30;
                    Log.i(TAG, "onTouchEvent: temp_distance:"+temp_distance);
                    if (temp_distance > 0.01f) {
                        rotate_count += 1;
                        RotateMethod.rotate(current_formula);
                        if (rotate_count > 30) {
                            rotate_count %= 30;
                            RotateMethod.rotateArray(current_formula);
                        }
                        temp_distance = 0;
                    }

                }

            } else if (ParaHandler.getIsmouseball()) {
                //设置观察或固定状态
                chaseCamera.setDragToRotate(false);
            }
        }
    }


    public static void main(String[] args) {
        //启动JME3程序
        HelloRubik app = new HelloRubik();
        app.start();

    }
}
