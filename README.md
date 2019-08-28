# postsProjRep

Score Alg: caculaed by post's creation date multiply by the post's votes.
Top posts: saved in memory, init when first approached, updated on post creation or votes update.
Project API: 
GET-   /post/topposts
POST-  /post/create
PUT-   /post/downvote
PUT-   /post/upvote
PUT-   /post/update

json input for post- 
{
id:
date:
content:
userId:
votes:
}
