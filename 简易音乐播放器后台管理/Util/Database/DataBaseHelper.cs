using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MusicPlayer.Database
{
    public static class DataBaseHelper
    {

        /// <summary>
        /// 查询数据
        /// </summary>
        /// <param name="sql"></param>
        /// <returns></returns>
        public static DataTable SelectData(string sql)
        {
            DataTable result = new DataTable();

            MySqlConnection conn = SqlHelper.GetSqlConn();

            try
            {
                conn.Open();
                MySqlDataReader reader = SqlHelper.SelectData(conn, sql);
                result.Load(reader);
            }
            catch (Exception e)
            {
                Debug.Print("出现查询错误"+e.Message);
            }
            finally
            {
                conn.Close();
            }

            return result;
        }

        /// <summary>
        /// 增删改
        /// </summary>
        /// <param name="sql"></param>
        /// <returns></returns>
        public static int ExecuteNonQuery(string sql)
        {
            int result = 0;

            MySqlConnection conn = SqlHelper.GetSqlConn();

            try
            {
                conn.Open();
                result = SqlHelper.ExecuteNonQuery(conn, sql);
            }
            catch (Exception e)
            {
                Debug.Print("出现错误" + e.Message);
            }
            finally
            {
                conn.Close();
            }

            return result;
        }
    }
}
