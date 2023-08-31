package com.dashingqi.module.debug.test;

/**
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.debug.test
 * @ClassName: DebugTestActivity
 * @Author: DashingQI
 * @CreateDate: 2020/10/26 7:31 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/10/26 7:31 AM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class DebugTestActivity {

    public void fun(){
        try{

        }catch (OutOfMemoryError error){

        }
    }


    public void test(){

        //返回Integer 会使用[-127,128]这个缓存 装箱操作
        Integer integer = Integer.valueOf(1);

        // parseInt不会使用缓存
        int i = Integer.parseInt("1");

        //拆箱操作
        int i1 = integer.intValue();

    }
}
