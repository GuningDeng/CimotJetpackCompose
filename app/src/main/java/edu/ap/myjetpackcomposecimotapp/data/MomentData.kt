package edu.ap.myjetpackcomposecimotapp.data

data class MomentItem(
    /**
     * avatar
     */
    val avatar: String,
    /**
     * name
     */
    val name: String,
    /**
     * content
     */
    val content: String,
    /**
     * image list
     */
    val images: List<String>,
    /**
     * create time
     */
    val createTime: String,
    /**
     * comment
     */
    val comment: String? = null,
)

data class ImageItem(
    val url: String
)

val myAvatar: String = "https://firebasestorage.googleapis.com/v0/b/cimot-app.appspot.com/o/images%2Fcimot_logo.png?alt=media"

val cimotLogo: String = "https://firebasestorage.googleapis.com/v0/b/cimot-app.appspot.com/o/images%2Fcimot_logo.png?alt=media"

val momentList = listOf(
    MomentItem(
        "https://xxx",
        "jan",
        "My content",
        listOf(
            "https://xxx",
            "https://xxx"
        ),
        "5 min before",
        "what?"
    )
)



