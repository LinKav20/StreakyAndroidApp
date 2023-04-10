package com.github.linkav20.network.api

import com.github.linkav20.network.models.changepasswordform.ChangePasswordFormBody
import com.github.linkav20.network.models.changepasswordform.ChangePasswordFormResponse
import com.github.linkav20.network.models.createnewtaskform.CreateTaskFormBody
import com.github.linkav20.network.models.createnewtaskform.CreateTaskFormResponse
import com.github.linkav20.network.models.existbyloginform.ExistsByLoginFormBody
import com.github.linkav20.network.models.existbyloginform.ExistsByLoginFormResponse
import com.github.linkav20.network.models.getcurrentdayform.GetCurrentDayFormBody
import com.github.linkav20.network.models.getcurrentdayform.GetCurrentDayFormResponse
import com.github.linkav20.network.models.getmytasksform.GetMyTasksFormBody
import com.github.linkav20.network.models.getmytasksform.GetMyTasksFormResponse
import com.github.linkav20.network.models.getnotifyform.GetNotifyFormBody
import com.github.linkav20.network.models.getnotifyform.GetNotifyFormResponse
import com.github.linkav20.network.models.getrandomuserform.GetRandomUserFormBody
import com.github.linkav20.network.models.getrandomuserform.GetRandomUserFormResponse
import com.github.linkav20.network.models.gettaskbyidform.GetTaskByIdFormBody
import com.github.linkav20.network.models.gettaskbyidform.GetTaskByIdFormResponse
import com.github.linkav20.network.models.getuserinfoform.GetUserInfoFormBody
import com.github.linkav20.network.models.getuserinfoform.GetUserInfoFormResponse
import com.github.linkav20.network.models.loginform.UserLoginFormBody
import com.github.linkav20.network.models.loginform.UserLoginFormResponse
import com.github.linkav20.network.models.sendnotificationform.SendNotificationBody
import com.github.linkav20.network.models.sendnotificationform.SendNotificationResponse
import com.github.linkav20.network.models.signupform.SignUpFormResponse
import com.github.linkav20.network.models.signupform.SignUpFormBody
import com.github.linkav20.network.models.tokenform.RefreshTokenResponse
import com.github.linkav20.network.models.updatemytaskform.UpdateMyTaskFormBody
import com.github.linkav20.network.models.updatemytaskform.UpdateMyTaskFormResponse
import com.github.linkav20.network.models.updateobserverdayform.UpdateObserverDayFormResponse
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("/sign_in")
    suspend fun login(@Body body: UserLoginFormBody): Response<UserLoginFormResponse>

    /*@POST("/") <-- TRUE
    suspend fun createTask(@Header("X-API-KEY") token: String)*/

    @GET("/refresh_token")
    suspend fun refreshToken(@Header("X-API-KEY") token: String): Response<RefreshTokenResponse>

    @POST("/sign_up")
    suspend fun signup(@Body body: SignUpFormBody): Response<SignUpFormResponse>

    @POST("/check_user_for_exists_by_login")
    suspend fun existByLogin(@Body body: ExistsByLoginFormBody): Response<ExistsByLoginFormResponse>

    @POST("/change_password")
    suspend fun changePassword(@Body body: ChangePasswordFormBody): Response<ChangePasswordFormResponse>

    @POST("/get_tasks")
    suspend fun getMyTasks(@Body body: GetMyTasksFormBody): Response<GetMyTasksFormResponse>

    @POST("/update_user_day")
    suspend fun updateMyTask(@Body body: UpdateMyTaskFormBody): Response<UpdateMyTaskFormResponse>

    @POST("/get_task_by_id")
    suspend fun getTaskById(@Body body: GetTaskByIdFormBody): Response<GetTaskByIdFormResponse>

    @POST("/get_current_day")
    suspend fun getCurrentDay(@Body body: GetCurrentDayFormBody): Response<GetCurrentDayFormResponse>

    @POST("/get_random_user")
    suspend fun getRandomUser(@Body body: GetRandomUserFormBody): Response<GetRandomUserFormResponse>

    @POST("/create_new_task")
    suspend fun createTask(@Body body: CreateTaskFormBody): Response<CreateTaskFormResponse>

    @POST("/send_notify")
    suspend fun sendNotification(@Body body: SendNotificationBody): Response<SendNotificationResponse>

    @POST("/update_observer_day")
    suspend fun updateObserverDay(@Body body: UpdateMyTaskFormBody): Response<UpdateObserverDayFormResponse>

    @POST("/get_user_info")
    suspend fun getUserInfo(@Body body: GetUserInfoFormBody): Response<GetUserInfoFormResponse>

    @POST("/get_notify")
    suspend fun getNotifications(@Body body: GetNotifyFormBody): Response<GetNotifyFormResponse>
}
