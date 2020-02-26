#  DCCT

1. 先安排几张APP图：

   <img src="image\launch.png" style="zoom:30%;" />             <img src="image\login.png" style="zoom:30%;" />            <img src="image\register.png" style="zoom:30%;" />
   <img src="image\ground.png" style="zoom:30%;" />             <img src="image\gauging.png" style="zoom:30%;" />            <img src="image\gauging.png" style="zoom:30%;" />    
   <img src="image\record.png" style="zoom:30%;" />             <img src="image\report.png" style="zoom:30%;" />

2. APP Version 1.0.1所实现的功能：

   - 登录注册：输入的邮箱格式检测、当第一次登录成功之后，杀死应用再次打开应用，将直接进入主界面；
   - 引导页面：只有在安装后第一次打开应用时才会展示；
   - 退出登录：在主界面点击左上角的用户名就可以退出登录；
   - 广场：向用户推荐药物相管的文章，后台服务器能实时更新推送内容；
   - 检测：提供了两种检查类型，当正确输入信息之后，在有返回结果的条件下，页面将跳转至报告页；
   - 记录：这里展示个人所有的查询记录（检测种类、名称、时间），点击记录将会跳转至查询结果页面；

3. 本项目我是第一此使用**Retrofit**，之前一直在使用**okhttp**，当然这也是比较好的工具，就我本次项目体验而言，Retrofit提供的注解对我们提升开发效率帮助很大，这个很类似与后台的SpringBoot，比如我们在RequestParam时，我们需要一个一个的构造参数，但是retrofit可以使用**@QueryMap**，如果只是单个参数，可以使用**@Query**。如果时传输路径变量则使用**@Path**，还有**@GET**、**@POST**等注解。

   ```java
   public interface API {
   
    @POST(Constant.LOGIN_API)
       Call<BackResultData<UserEntity>> submitLoginData(@Body LoginUserEntity loginUserEntity);
   
       @POST(Constant.REGISTER_API)
       Call<BackResultData> submitRegisterData(@Body RegisterUserEntity registerUserEntity);
   
       @GET(Constant.COVER_API)
       Call<BackResultData<List<CoverEntity>>> getImageUrl();
   
       @POST(Constant.QUERY_API)
       Call<BackResultData<List<QueryResultEntity>>> submitQueryData(@Body PostQueryEntity postQueryEntity);
   
       @GET(Constant.RECORD_API)
       Call<BackResultData<List<Record>>> getRecords(@Path("uid") long id);
   
       @POST(Constant.SIGN_OUT)
       Call<BackResultData> subSignOutId(@Path( "uid" ) long id);
   }
   ```
   
4. - 本项目是我自己更加熟练的掌握了**View**之间的各种形式的传递数据
   - 对**Activity**四种启动模式有比较深入理解
   - 如何在**Activity**异常退出，重新进入时还原之前界面数据信息
   - 了解了**Serializable**、**Parcelable**，实现对象序列化比较两者的优缺点，在**Intent**在两活动之间传输数据和**SpringBoot** Entity中都使用了对象序列化
   - **TabLayout**与**ViewPager**联合使用，**Toolbar**与**TabLayout**联合使用，避免了再单独定义item_top子布局
   - **沉浸式界面**运用（启动界面和引导页面），注意修改状态栏字体颜色修改（默认白色）

5. 最令我开心的事情是我完成了**屏幕适配**：

   - **[字节跳动的屏幕适配方案](https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA)**
   
   ```java
   public static float sNoncompatDensity;
   public static float sNoncompatScaledDensity;
   
   public static void setCustomDensity(Activity activity, final Application application){
       final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
   
       if (sNoncompatDensity == 0){
           sNoncompatDensity = appDisplayMetrics.density;
           sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
           application.registerComponentCallbacks( new ComponentCallbacks() {
               @Override
               public void onConfigurationChanged(@NonNull Configuration newConfig) {
                   if (newConfig.fontScale > 0) {
                       sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                   }
               }
   
               @Override
               public void onLowMemory() {
   
               }
           } );
       }
       
       final float targetDensity = (float) appDisplayMetrics.widthPixels / 360;
       System.out.println(appDisplayMetrics.widthPixels);
       System.out.println(targetDensity);
       final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
       final int targetDensityDpi = (int) (160 * targetDensity);
   
       appDisplayMetrics.density = targetDensity;
       appDisplayMetrics.densityDpi = targetDensityDpi;
       appDisplayMetrics.scaledDensity = targetScaledDensity;
   
       final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
       activityDisplayMetrics.density = targetDensity;
       activityDisplayMetrics.scaledDensity = targetScaledDensity;
       activityDisplayMetrics.densityDpi = targetDensityDpi;
   
   }
   ```
   
   > 在Activity的onCreate方法中调用调用即可
   >
   > 原文章 **final float targetDensity = (float) appDisplayMetrics.widthPixels / 360**,这里的**appDisplayMetrics.widthPixels**并没有强转为float类型，实际上这里的强转是必须要的。
   
   - **[ AndroidAutoSize](https://github.com/JessYanCoding/AndroidAutoSize)**
   
   > 同样是基于字节跳动的屏幕适配原理，但是作者在此基础上做了很好的优化，而且使用教程也是一看就懂。我的项目做使用了此框架，原始的字节跳动屏幕适配方案我也尝试过，效果上差别是不大的（未牵扯到限定某页面不使用屏幕适配的情况），前者使用需要在每个Activity中进行调用适配方法，没有后者使用方便，而且后者也对Fragment做很很好的处理，并且对老项目也提供的使用方法。
   
   ```
   implementation 'me.jessyan:autosize:1.2.0'
   ```

