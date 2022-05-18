
namespace MusicPlayer
{
    partial class MainForm
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.btn_personnelManagement = new System.Windows.Forms.Button();
            this.btn_musicManagement = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btn_personnelManagement
            // 
            this.btn_personnelManagement.Location = new System.Drawing.Point(148, 81);
            this.btn_personnelManagement.Name = "btn_personnelManagement";
            this.btn_personnelManagement.Size = new System.Drawing.Size(148, 61);
            this.btn_personnelManagement.TabIndex = 0;
            this.btn_personnelManagement.Text = "人员管理";
            this.btn_personnelManagement.UseVisualStyleBackColor = true;
            this.btn_personnelManagement.Click += new System.EventHandler(this.btn_personnelManagement_Click);
            // 
            // btn_musicManagement
            // 
            this.btn_musicManagement.Location = new System.Drawing.Point(148, 179);
            this.btn_musicManagement.Name = "btn_musicManagement";
            this.btn_musicManagement.Size = new System.Drawing.Size(148, 61);
            this.btn_musicManagement.TabIndex = 1;
            this.btn_musicManagement.Text = "歌曲管理";
            this.btn_musicManagement.UseVisualStyleBackColor = true;
            this.btn_musicManagement.Click += new System.EventHandler(this.btn_musicManagement_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 18F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(453, 332);
            this.Controls.Add(this.btn_musicManagement);
            this.Controls.Add(this.btn_personnelManagement);
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "音乐播放器后台管理";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btn_personnelManagement;
        private System.Windows.Forms.Button btn_musicManagement;
    }
}

