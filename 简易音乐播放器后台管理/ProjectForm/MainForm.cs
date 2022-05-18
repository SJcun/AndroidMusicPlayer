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
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 人员管理
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btn_personnelManagement_Click(object sender, EventArgs e)
        {
            PersonMangeForm personMangeForm = new PersonMangeForm();
            personMangeForm.ShowDialog();
        }

        /// <summary>
        /// 歌曲管理
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btn_musicManagement_Click(object sender, EventArgs e)
        {
            MusicMangeForm musicMangeForm = new MusicMangeForm();
            musicMangeForm.ShowDialog();
        }
    }
}
