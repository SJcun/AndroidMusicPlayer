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
    public partial class MusicMangeForm : Form
    {
        public MusicMangeForm()
        {
            InitializeComponent();
        }

        private void MusicMangeForm_Load(object sender, EventArgs e)
        {
            RefreshData();
        }

        private void RefreshData(string whereStr = "")
        {
            string sql = "select * from music"+whereStr;
            DataTable dt = DataBaseHelper.SelectData(sql);
            if (dt.Rows.Count > 0)
            {
                dgv_music.DataSource = dt;
            }
        }

        private void Btn_insertUser_Click(object sender, EventArgs e)
        {
            if(tbx_musicName.Text.Equals("") || tbx_author.Text.Equals("") || tbx_address.Text.Equals("") || tbx_image.Text.Equals(""))
            {
                MessageBox.Show("相关信息未填写", "提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string sql = "insert into music(name,author,address,img,create_time,remark) values('" + tbx_musicName.Text + "','" + tbx_author.Text + "','"+ tbx_address.Text+"','" + tbx_image.Text+"',now(),'"+tbx_remark.Text+"')";
            int rows = DataBaseHelper.ExecuteNonQuery(sql);
            if (rows > 0)
            {
                RefreshData();
                MessageBox.Show("添加成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        /// <summary>
        /// 根据歌曲名称查询
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Btn_select_Click(object sender, EventArgs e)
        {
            if (tbx_musicName.Text.Equals(""))
            {
                MessageBox.Show("歌曲名称未填写", "提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string whereStr = " where name = '" + tbx_musicName.Text + "'";
            RefreshData(whereStr);
        }

        private void Btn_update_Click(object sender, EventArgs e)
        {
            if (tbx_musicName.Text.Equals("") || tbx_author.Text.Equals("") || tbx_address.Text.Equals("") || tbx_image.Text.Equals(""))
            {
                MessageBox.Show("相关信息未填写", "提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string id = dgv_music.SelectedRows[0].Cells["musicId"].Value.ToString();

            string sql = "update music set name='" + tbx_musicName.Text + "',author='" + tbx_author.Text + "',address='" + tbx_address.Text + "', change_time=now(),img='"+tbx_image.Text+ "',remark='"+tbx_remark.Text+ "' where music_id = " + id;
            int rows = DataBaseHelper.ExecuteNonQuery(sql);
            if (rows > 0)
            {
                RefreshData();
                MessageBox.Show("修改成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void Btn_delete_Click(object sender, EventArgs e)
        {
            string id = dgv_music.SelectedRows[0].Cells["musicId"].Value.ToString();

            string sql = "delete from music where music_id=" + id;
            int rows = DataBaseHelper.ExecuteNonQuery(sql);
            if (rows > 0)
            {
                RefreshData();
                MessageBox.Show("删除成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void dgv_music_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                tbx_musicName.Text = dgv_music.SelectedRows[0].Cells["name"].Value.ToString();
                tbx_author.Text = dgv_music.SelectedRows[0].Cells["author"].Value.ToString();
                tbx_address.Text = dgv_music.SelectedRows[0].Cells["address"].Value.ToString();
                tbx_image.Text = dgv_music.SelectedRows[0].Cells["img"].Value.ToString();
                tbx_remark.Text = dgv_music.SelectedRows[0].Cells["remark"].Value.ToString();
            }
        }
    }
}
