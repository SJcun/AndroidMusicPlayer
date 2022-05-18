using MySql.Data.MySqlClient;

namespace MusicPlayer.Database
{
    public static class SqlHelper
    {
        /// <summary>
        /// 数据库连接串
        /// </summary>
        private static string sqlConnectionString { get; set; }

        /// <summary>
        /// mysql数据库实体
        /// </summary>
        private static MySqlConnection conn = null;

        /// <summary>
        /// 静态代码块
        /// </summary>
        static SqlHelper()
        {
            string path = System.Windows.Forms.Application.StartupPath+ "\\Manage.ini";
            sqlConnectionString = IniFileHelper.GetInstance(path).GetIniString("DataBase", "sqlConnectionString");
        }

        /// <summary>
        /// 获取数据库连接
        /// </summary>
        /// <returns></returns>
        public static MySqlConnection GetSqlConn()
        {
            if (conn == null)
            {
                conn = new MySqlConnection(sqlConnectionString);
            }
            return conn;
        }

        /// <summary>
        /// 查询数据公共方法
        /// </summary>
        /// <param name="conn">数据库连接</param>
        /// <param name="sql">sql语句</param>
        /// <returns>查询到的数据</returns>
        public static MySqlDataReader SelectData(MySqlConnection conn, string sql)
        {
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            MySqlDataReader result = cmd.ExecuteReader();

            return result;
        }

        /// <summary>
        /// 增、删、改公共方法
        /// </summary>
        /// <param name="conn"></param>
        /// <param name="sql"></param>
        /// <returns></returns>
        public static int ExecuteNonQuery(MySqlConnection conn, string sql)
        {
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            int result = cmd.ExecuteNonQuery();
            return result;
        }
    }
}
