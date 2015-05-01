public class Main implements SQLConstants {
	public static void main(String[] args) {
		String query = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`, `q`.`user_name`, q.`answers` , "
				+ "q.`reputation`, `o9_INSTITUTE_ID_tags`.`tag_id`,`o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`,  "
				+ "`o9_INSTITUTE_ID_tags`.`tag_reputation`, q.liked "
				+ "from( "
				+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, d.`answers`, "
				+ "d.`reputation` , count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as liked  "
				+ "from ( "
				+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, `c`.`user_name`, c.`answers` ,"
				+ "	count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation  "
				+ "FROM ( "
				+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
				+ "count(`o9_INSTITUTE_ID_post_answer`.`answer_id`) as answers  "
				+ "FROM (   "
				+ "Select `o9_INSTITUTE_ID_post`.`post_id`,`o9_INSTITUTE_ID_post`.`post_title`, "
				+ "`o9_INSTITUTE_ID_post`.`post_content`,`o9_INSTITUTE_ID_post`.`post_time`, "
				+ "`o9_INSTITUTE_ID_post`.`user_id`, `o9_INSTITUTE_ID_forum_users`.`user_name`"
				+ "FROM `o9_INSTITUTE_ID_post`  "
				+ "left join `o9_INSTITUTE_ID_forum_users` on `o9_INSTITUTE_ID_forum_users`.`user_id` = `o9_INSTITUTE_ID_post`.`user_id`  "
				+ "where `o9_INSTITUTE_ID_post`.`user_id`  = 'USER_ID' limit 0,10"
				+ ") as a "
				+ "left join `o9_INSTITUTE_ID_post_answer` on `o9_INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
				+ "group by   a.post_id"
				+ "	) as c "
				+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` "
				+ "group by c.`post_id` "
				+ ") as d "
				+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` "
				+ "group by d.`post_id`"
				+ "	) as q "
				+ "left join `o9_INSTITUTE_ID_post_tag_map` on `o9_INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
				+ "left join `o9_INSTITUTE_ID_tags` on `o9_INSTITUTE_ID_tags`.`tag_id` = `o9_INSTITUTE_ID_post_tag_map`.`tag_id` order by  q.post_time desc ;";
		query = query.replaceAll("INSTITUTE_ID", "100000001");
		query = query.replaceAll("USER_ID", "300000002");
		System.out.println(query);
	}
}
