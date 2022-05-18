using MusicPlayer.Database;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MusicPlayer
{
    public partial class PersonMangeForm : Form
    {


        public PersonMangeForm()
        {
            InitializeComponent();
        }

        private void PersonMangeForm_Load(object sender, EventArgs e)
        {
            RefreshData();
        }

        private void RefreshData(string whereStr = "")
        {
            string sql = "select * from user"+ whereStr;
            DataTable dt = DataBaseHelper.SelectData(sql);
            if (dt.Rows.Count > 0)
            {
                dgv_personInfo.DataSource = dt;
            }
        }

        /// <summary>
        /// 单元格选中事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void dgv_personInfo_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                tbx_account.Text = dgv_personInfo.SelectedRows[0].Cells["account"].Value.ToString();
                tbx_password.Text = dgv_personInfo.SelectedRows[0].Cells["password"].Value.ToString();
                tbx_remark.Text = dgv_personInfo.SelectedRows[0].Cells["remark"].Value.ToString();
                if (dgv_personInfo.SelectedRows[0].Cells["pattern"].Value.ToString().Equals("0"))
                {
                    cbx_pattern.Text = "顺序播放";
                }
                else if (dgv_personInfo.SelectedRows[0].Cells["pattern"].Value.ToString().Equals("1"))
                {
                    cbx_pattern.Text = "随机播放";
                }
                else
                {
                    cbx_pattern.Text = "单曲循环";
                }
            }
        }

        private void Btn_insertUser_Click(object sender, EventArgs e)
        {
            if(tbx_account.Text.Equals("") || tbx_password.Text.Equals(""))
            {
                MessageBox.Show("账户名或密码未填写","提示",MessageBoxButtons.OK,MessageBoxIcon.Error);
                return;
            }

            string sql = "insert into user(`account`, `password`, `create_time`) values('"+tbx_account.Text+"','"+tbx_password.Text+"',now())";
            int rows = DataBaseHelper.ExecuteNonQuery(sql);
            if (rows > 0)
            {
                RefreshData();
                MessageBox.Show("添加成功","提示",MessageBoxButtons.OK,MessageBoxIcon.Information);
            }
        }

        /// <summary>
        /// 根据账户名查询数据
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Btn_select_Click(object sender, EventArgs e)
        {
            if (tbx_account.Text.Equals(""))
            {
                MessageBox.Show("账户名未填写", "提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string whereStr = " where account = '"+tbx_account.Text+"'";
            RefreshData(whereStr);
        }

        /// <summary>
        /// 修改数据
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Btn_update_Click(object sender, EventArgs e)
        {
            if (tbx_account.Text.Equals("") || tbx_password.Text.Equals(""))
            {
                MessageBox.Show("账户名或密码未填写", "提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string id = dgv_personInfo.SelectedRows[0].Cells["userid"].Value.ToString();
            string pattern = "0";
            if (cbx_pattern.Text.Equals("顺序播放"))
            {
                pattern = "0";
            }
            else if (cbx_pattern.Text.Equals("随机播放"))
            {
                pattern = "1";
            }
            else
            {
                pattern = "2";
            }

            string sql = "update user set account='"+ tbx_account.Text + "',password='"+ tbx_password.Text + "',pattern="+pattern+", change_time=now() where userid = "+id;
            int rows = DataBaseHelper.ExecuteNonQuery(sql);
            if (rows > 0)
            {
                RefreshData();
                MessageBox.Show("修改成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void Btn_delete_Click(object sender, EventArgs e)
        {
            string id = dgv_personInfo.SelectedRows[0].Cells["userid"].Value.ToString();
            string sql = "delete from user where userid="+id;
            int rows = DataBaseHelper.ExecuteNonQuery(sql);
            if (rows > 0)
            {
                RefreshData();
                MessageBox.Show("删除成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }
    }
}
