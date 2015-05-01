

public interface SQLConstants {
	String GET_GROUPS = "select `institute_INSTITUTE_ID_forum`.`group_id`, `institute_INSTITUTE_ID_student`"
			+ ".`name` as admin_name, `institute_INSTITUTE_ID_forum`.`group_admin`, `institute_INSTITUTE_ID_forum`."
			+ "`group_name`, `institute_INSTITUTE_ID_forum`.`group_reputation`,"
			+ " count(`institute_INSTITUTE_ID_forum_member`.`id`) as no_of_members"
			+ " from institute_INSTITUTE_ID_forum left join institute_INSTITUTE_ID_forum_member"
			+ " on institute_INSTITUTE_ID_forum.group_id = institute_INSTITUTE_ID_forum_member.group_id"
			+ " left join institute_INSTITUTE_ID_student on "
			+ "institute_INSTITUTE_ID_forum.group_admin = institute_INSTITUTE_ID_student.id"
			+ " where `institute_INSTITUTE_ID_forum`.`group_name` LIKE_DATA "
			+ "group by `institute_INSTITUTE_ID_forum`.`group_id` ORDER_DATA ;";
	
	String COUNT_GROUPS = "SELECT count(*) as count from  institute_INSTITUTE_ID_forum;";
	
	
	String GET_TAGS = "SELECT `INSTITUTE_ID_tags`.`tag_id`, `INSTITUTE_ID_tags`.`tag_name`, `INSTITUTE_ID_tags`.`tag_desc`,"
			+ "`INSTITUTE_ID_tags`.`tag_reputation` FROM `o9paathshala`.`INSTITUTE_ID_tags` where `INSTITUTE_ID_tags`.`tag_name`"
			+ " LIKE_DATA  ORDER_DATA ;";
	
	String UPDATE_TAG_REPUTATION = "UPDATE `o9paathshala`.`INSTITUTE_ID_tags` SET `tag_reputation` = `tag_reputation` + 1 WHERE `tag_id` = ?;";
	
	String POST_QUESTION = "INSERT INTO `o9paathshala`.`INSTITUTE_ID_post` "
			+ "(`post_title`,`post_content`,`user_id`,`post_time`) "
			+ "VALUES(?,?,?,now());";
	
	String QUESTION_TAG_MAP = "INSERT INTO `o9paathshala`.`INSTITUTE_ID_post_tag_map`"
			+ "(`post_id`,`tag_id`)"
			+ "VALUES(LAST_INSERT_ID(),?);";

	/*String GET_QUESTION2 = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`, "
			+ "`q`.`user_name`, q.`answers` ,q.`reputation`, q.liked , `INSTITUTE_ID_tags`.`tag_id`, `INSTITUTE_ID_tags`.`tag_name`, `INSTITUTE_ID_tags`.`tag_desc`, `INSTITUTE_ID_tags`.`tag_reputation` "
			+ "from( "
			+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, d.`answers` "
			+ ",d.`reputation` , count(`INSTITUTE_ID_post_like_user_map`.`user_id`) as liked "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, `c`.`user_name`, c.`answers` , "
			+ "count(`INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`INSTITUTE_ID_post_answer`.`answer_id`) as answers FROM ( "
			+ "Select `INSTITUTE_ID_post`.`post_id`, `INSTITUTE_ID_post`.`post_title`, `INSTITUTE_ID_post`.`post_content`, `INSTITUTE_ID_post`.`post_time`, `INSTITUTE_ID_post`.`user_id`, "
			+ "`INSTITUTE_ID_user`.`user_name` FROM `INSTITUTE_ID_post` left join `INSTITUTE_ID_user` on `INSTITUTE_ID_user`.`user_id` = `INSTITUTE_ID_post`.`user_id` "
			+ " FOR_MINE_TAB ) as a "
			+ "left join `INSTITUTE_ID_post_answer` on `INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` group by `a`.`post_id` "
			+ " FOR_UNANSWERED_TAB "
			+ " limit ?,? ) as c "
			+ "left join `INSTITUTE_ID_post_like_user_map` on `INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` group by c.`post_id`) as d "
			+ "left join `INSTITUTE_ID_post_like_user_map` on `INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` and INSTITUTE_ID_post_like_user_map.`user_id` = ? "
			+ " group by d.`post_id`) as q "
			+ "left join `INSTITUTE_ID_post_tag_map` on `INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `INSTITUTE_ID_tags` on `INSTITUTE_ID_tags`.`tag_id` = `INSTITUTE_ID_post_tag_map`.`tag_id` "
			+ "where `q`.`post_title` like 'LIKE_DATA%' "
			+ " order by  q.SORT_DATA desc ;";*/
	
	String GET_QUESTION = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`, "
			+ "`q`.`user_name`, q.`answers` ,q.`reputation`, q.liked , `INSTITUTE_ID_tags`.`tag_id`, `INSTITUTE_ID_tags`.`tag_name`, `INSTITUTE_ID_tags`.`tag_desc`, `INSTITUTE_ID_tags`.`tag_reputation`"
			+ " from( "
			+ "	Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, d.`answers` "
			+ ",d.`reputation` , count(`INSTITUTE_ID_post_like_user_map`.`user_id`) as liked "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, `c`.`user_name`, c.`answers` ,"
			+ "	count(`INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation "
			+ "FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`INSTITUTE_ID_post_answer`.`answer_id`) as answers "
			+ "FROM ( "
			+ "Select `INSTITUTE_ID_post`.`post_id`, `INSTITUTE_ID_post`.`post_title`, `INSTITUTE_ID_post`.`post_content`, "
			+ "`INSTITUTE_ID_post`.`post_time`, `INSTITUTE_ID_post`.`user_id`, `INSTITUTE_ID_user`.`user_name` "
			+ "FROM `INSTITUTE_ID_post` "
			+ "left join `INSTITUTE_ID_user` on `INSTITUTE_ID_user`.`user_id` = `INSTITUTE_ID_post`.`user_id` "
			+ " FOR_MINE_TAB "
			+ "group by `INSTITUTE_ID_post`.`post_id`  limit LIMIT_START,LIMIT_END"
			+ ") as a "
			+ "left join `INSTITUTE_ID_post_answer` on `INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
			+ "group by `a`.`post_id` "
			+ " FOR_UNANSWERED_TAB "
			+ ") as c "
			+ "left join `INSTITUTE_ID_post_like_user_map` on `INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` group by c.`post_id`) as d "
			+ "left join `INSTITUTE_ID_post_like_user_map` on `INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` and INSTITUTE_ID_post_like_user_map.`user_id` = USER_ID "
			+ "group by d.`post_id`  "
			+ ") as q "
			+ "left join `INSTITUTE_ID_post_tag_map` on `INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `INSTITUTE_ID_tags` on `INSTITUTE_ID_tags`.`tag_id` = `INSTITUTE_ID_post_tag_map`.`tag_id` "
			+ "where `q`.`post_title` like 'LIKE_DATA%'  "
			+ "order by  q.SORT_DATA desc ;";
	
	
	String GET_TAGS_OF_A_QUESTION = "SELECT `INSTITUTE_ID_tags`.`tag_id`,`INSTITUTE_ID_tags`.`tag_name`, `INSTITUTE_ID_tags`.`tag_desc`, `INSTITUTE_ID_tags`.`tag_reputation` "
			+ "FROM `o9paathshala`.`INSTITUTE_ID_post_tag_map` "
			+ "left join `INSTITUTE_ID_tags`  on `INSTITUTE_ID_tags`.`tag_id` = `INSTITUTE_ID_post_tag_map`.`tag_id` "
			+ "where `INSTITUTE_ID_post_tag_map`.`post_id` = ? group by `INSTITUTE_ID_post_tag_map`.`tag_id`;";
	
	String GET_LAST_INCREMENTED_ID = "SELECT LAST_INSERT_ID();";
	
	String UPDATE_QUESTION_LIKE = "INSERT INTO `o9paathshala`.`INSTITUTE_ID_post_like_user_map` (`post_id`,`user_id`) VALUES(?,?);";
	
	String UPDATE_QUESTION_UNLIKE = "DELETE FROM `o9paathshala`.`INSTITUTE_ID_post_like_user_map` WHERE post_id = ? and user_id = ? ;";
	
	String UPDATE_ANSWER_LIKE = "INSERT INTO `o9paathshala`.`INSTITUTE_ID_post_answer_like_map` (`answer_id`,`user_id`) VALUES(?,?);";
	
	String UPDATE_ANSWER_UNLIKE = "DELETE FROM `o9paathshala`.`INSTITUTE_ID_post_answer_like_map` WHERE answer_id = ? and user_id = ? ;";
	
	String POST_ANSWER = "INSERT INTO `o9paathshala`.`INSTITUTE_ID_post_answer` "
			+ "(`post_id`,`user_id`,`answer_time`,`answer_content`) "
			+ "VALUES( ? , ?  ,now(), ? );";
	
	String GET_POST_ANSWER = "select `a`.`answer_id`, `a`.`user_id`, `a`.`answer_content`, `a`.`user_name`, `a`.`reputation`, `a`.`answer_time`, "
			+ "count(`INSTITUTE_ID_post_answer_like_map`.`answer_id`) as liked "
			+ "from( "
			+ "SELECT `INSTITUTE_ID_post_answer`.`answer_id`,`INSTITUTE_ID_post_answer`.`post_id` ,`INSTITUTE_ID_post_answer`.`user_id`, `INSTITUTE_ID_post_answer`.`answer_time`, "
			+ "`INSTITUTE_ID_post_answer`.`answer_content`,`INSTITUTE_ID_user`.`user_name`, count(`INSTITUTE_ID_post_answer_like_map`.`answer_id`) as reputation "
			+ "from `INSTITUTE_ID_post_answer`left join `INSTITUTE_ID_user` on `INSTITUTE_ID_user`.`user_id` = `INSTITUTE_ID_post_answer`.`user_id` "
			+ "left join `INSTITUTE_ID_post_answer_like_map` on `INSTITUTE_ID_post_answer_like_map`.`answer_id` = `INSTITUTE_ID_post_answer`.`answer_id` "
			+ "group by `INSTITUTE_ID_post_answer`.`answer_id`) as a "
			+ "left join `INSTITUTE_ID_post_answer_like_map` on `a`.`answer_id` = `INSTITUTE_ID_post_answer_like_map`.`answer_id` and `INSTITUTE_ID_post_answer_like_map`.`user_id` = ? "
			+ "where `a`.`post_id` = ? "
			+ "group by `a`.`answer_id` "
			+ "order by `a`.`answer_time` desc;";
	
	
	String GET_EXPLORED_QUESTION = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`,`q`.`user_name`, "
			+ "q.`answers` ,q.`reputation`, q.liked , `INSTITUTE_ID_tags`.`tag_id`, `INSTITUTE_ID_tags`.`tag_name`, `INSTITUTE_ID_tags`.`tag_desc`, `INSTITUTE_ID_tags`.`tag_reputation` "
			+ "from( "
			+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, "
			+ "d.`answers` ,d.`reputation` , count(`INSTITUTE_ID_post_like_user_map`.`user_id`) as liked "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, "
			+ "`c`.`user_name`, c.`answers` ,count(`INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation "
			+ "FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`INSTITUTE_ID_post_answer`.`answer_id`) as answers "
			+ "FROM ( "
			+ "Select `INSTITUTE_ID_post`.`post_id`, `INSTITUTE_ID_post`.`post_title`, `INSTITUTE_ID_post`.`post_content`, `INSTITUTE_ID_post`.`post_time`, `INSTITUTE_ID_post`.`user_id`, `INSTITUTE_ID_user`.`user_name` "
			+ "FROM `INSTITUTE_ID_post` left join `INSTITUTE_ID_user` on `INSTITUTE_ID_user`.`user_id` = `INSTITUTE_ID_post`.`user_id` "
			+ "where `INSTITUTE_ID_post`.`post_id` = ? "
			+ "group by `INSTITUTE_ID_post`.`post_id` "
			+ ") as a "
			+ "left join `INSTITUTE_ID_post_answer` on `INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
			+ "group by `a`.`post_id` "
			+ ") as c "
			+ "left join `INSTITUTE_ID_post_like_user_map` on `INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` "
			+ "group by c.`post_id` "
			+ ") as d "
			+ "left join `INSTITUTE_ID_post_like_user_map` on `INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` and INSTITUTE_ID_post_like_user_map.`user_id` = ? "
			+ "group by d.`post_id`  "
			+ ") as q "
			+ "left join `INSTITUTE_ID_post_tag_map` on `INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `INSTITUTE_ID_tags` on `INSTITUTE_ID_tags`.`tag_id` = `INSTITUTE_ID_post_tag_map`.`tag_id` ;";
}

