package com.github.linkav20.network.api

import com.github.linkav20.network.models.changepasswordform.ChangePasswordFormBody
import com.github.linkav20.network.models.changepasswordform.ChangePasswordFormRequest
import com.github.linkav20.network.models.createnewtaskform.CreateTaskFormBody
import com.github.linkav20.network.models.createnewtaskform.CreateTaskFormRequest
import com.github.linkav20.network.models.existbyloginform.ExistsByLoginFormBody
import com.github.linkav20.network.models.existbyloginform.ExistsByLoginFormRequest
import com.github.linkav20.network.models.getcurrentdayform.GetCurrentDayFormBody
import com.github.linkav20.network.models.getcurrentdayform.GetCurrentDayFormRequest
import com.github.linkav20.network.models.getmytasksform.GetMyTasksFormBody
import com.github.linkav20.network.models.getmytasksform.GetMyTasksFormRequest
import com.github.linkav20.network.models.getnotifyform.GetNotifyFormBody
import com.github.linkav20.network.models.getnotifyform.GetNotifyFormRequest
import com.github.linkav20.network.models.getrandomuserform.GetRandomUserFormBody
import com.github.linkav20.network.models.getrandomuserform.GetRandomUserFormRequest
import com.github.linkav20.network.models.gettaskbyidform.GetTaskByIdFormBody
import com.github.linkav20.network.models.gettaskbyidform.GetTaskByIdFormRequest
import com.github.linkav20.network.models.getuserinfoform.GetUserInfoFormBody
import com.github.linkav20.network.models.getuserinfoform.GetUserInfoFormRequest
import com.github.linkav20.network.models.loginform.UserLoginFormBody
import com.github.linkav20.network.models.loginform.UserLoginFormResponse
import com.github.linkav20.network.models.sendnotificationform.SendNotificationBody
import com.github.linkav20.network.models.sendnotificationform.SendNotificationRequest
import com.github.linkav20.network.models.signupform.SignUoFormRequest
import com.github.linkav20.network.models.signupform.SignUpFormBody
import com.github.linkav20.network.models.tokenform.RefreshTokenRequest
import com.github.linkav20.network.models.updatemytaskform.UpdateMyTaskFormBody
import com.github.linkav20.network.models.updatemytaskform.UpdateMyTaskFormRequest
import com.github.linkav20.network.models.updateobserverdayform.UpdateObserverDayFormRequest
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("/sign_in")
    suspend fun login(@Body body: UserLoginFormBody): Response<UserLoginFormResponse>

    /*@POST("/") <-- TRUE
    suspend fun createTask(@Header("X-API-KEY") token: String)*/

    @GET("/refresh_token")
    suspend fun refreshToken(@Header("X-API-KEY") token: String): Response<RefreshTokenRequest>

    @POST("/sign_up")
    suspend fun signup(@Body body: SignUpFormBody): Response<SignUoFormRequest>

    @POST("/check_user_for_exists_by_login")
    suspend fun existByLogin(@Body body: ExistsByLoginFormBody): Response<ExistsByLoginFormRequest>

    @POST("/change_password")
    suspend fun changePassword(@Body body: ChangePasswordFormBody): Response<ChangePasswordFormRequest>

    @POST("/get_tasks")
    suspend fun getMyTasks(@Body body: GetMyTasksFormBody): Response<GetMyTasksFormRequest>

    @POST("/update_user_day")
    suspend fun updateMyTask(@Body body: UpdateMyTaskFormBody): Response<UpdateMyTaskFormRequest>

    @POST("get_task_by_id")
    suspend fun getTaskById(@Body body: GetTaskByIdFormBody): Response<GetTaskByIdFormRequest>

    @POST("get_current_day")
    suspend fun getCurrentDay(@Body body: GetCurrentDayFormBody): Response<GetCurrentDayFormRequest>

    @POST("get_random_user")
    suspend fun getRandomUser(@Body body: GetRandomUserFormBody): Response<GetRandomUserFormRequest>

    @POST("create_new_task")
    suspend fun createTask(@Body body: CreateTaskFormBody): Response<CreateTaskFormRequest>

    @POST("send_notify")
    suspend fun sendNotification(@Body body: SendNotificationBody): Response<SendNotificationRequest>

    @POST("update_observer_day")
    suspend fun updateObserverDay(@Body body: UpdateMyTaskFormBody): Response<UpdateObserverDayFormRequest>

    @POST("/get_user_info")
    suspend fun getUserInfo(@Body body: GetUserInfoFormBody): Response<GetUserInfoFormRequest>

    @POST("/get_notify")
    suspend fun getNotifications(@Body body: GetNotifyFormBody): Response<GetNotifyFormRequest>

    @DELETE("/delete_user")
    suspend fun deleteUser()
}
