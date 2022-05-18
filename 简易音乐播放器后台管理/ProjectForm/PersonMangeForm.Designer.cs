
namespace MusicPlayer
{
    partial class PersonMangeForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dgv_personInfo = new System.Windows.Forms.DataGridView();
            this.panel1 = new System.Windows.Forms.Panel();
            this.userid = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.account = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.password = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.musicId = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pattern = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.create_time = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.change_time = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.remark = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.tbx_account = new System.Windows.Forms.TextBox();
            this.tbx_password = new System.Windows.Forms.TextBox();
            this.tbx_remark = new System.Windows.Forms.TextBox();
            this.Btn_insertUser = new System.Windows.Forms.Button();
            this.Btn_update = new System.Windows.Forms.Button();
            this.Btn_select = new System.Windows.Forms.Button();
            this.Btn_delete = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.cbx_pattern = new System.Windows.Forms.ComboBox();
            ((System.ComponentModel.ISupportInitialize)(this.dgv_personInfo)).BeginInit();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // dgv_personInfo
            // 
            this.dgv_personInfo.AllowUserToAddRows = false;
            this.dgv_personInfo.AllowUserToDeleteRows = false;
            this.dgv_personInfo.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgv_personInfo.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgv_personInfo.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.userid,
            this.account,
            this.password,
            this.musicId,
            this.pattern,
            this.create_time,
            this.change_time,
            this.remark});
            this.dgv_personInfo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgv_personInfo.Location = new System.Drawing.Point(0, 0);
            this.dgv_personInfo.Name = "dgv_personInfo";
            this.dgv_personInfo.ReadOnly = true;
            this.dgv_personInfo.RowHeadersVisible = false;
            this.dgv_personInfo.RowHeadersWidth = 62;
            this.dgv_personInfo.RowTemplate.Height = 30;
            this.dgv_personInfo.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgv_personInfo.Size = new System.Drawing.Size(1043, 534);
            this.dgv_personInfo.TabIndex = 0;
            this.dgv_personInfo.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgv_personInfo_CellClick);
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.dgv_personInfo);
            this.panel1.Location = new System.Drawing.Point(12, 186);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1043, 534);
            this.panel1.TabIndex = 2;
            // 
            // userid
            // 
            this.userid.DataPropertyName = "userid";
            this.userid.FillWeight = 60F;
            this.userid.HeaderText = "编号";
            this.userid.MinimumWidth = 8;
            this.userid.Name = "userid";
            this.userid.ReadOnly = true;
            // 
            // account
            // 
            this.account.DataPropertyName = "account";
            this.account.HeaderText = "账户名称";
            this.account.MinimumWidth = 8;
            this.account.Name = "account";
            this.account.ReadOnly = true;
            // 
            // password
            // 
            this.password.DataPropertyName = "password";
            this.password.HeaderText = "密码";
            this.password.MinimumWidth = 8;
            this.password.Name = "password";
            this.password.ReadOnly = true;
            // 
            // musicId
            // 
            this.musicId.DataPropertyName = "music_id";
            this.musicId.HeaderText = "歌曲编号";
            this.musicId.MinimumWidth = 8;
            this.musicId.Name = "musicId";
            this.musicId.ReadOnly = true;
            // 
            // pattern
            // 
            this.pattern.DataPropertyName = "pattern";
            this.pattern.HeaderText = "播放模式";
            this.pattern.MinimumWidth = 8;
            this.pattern.Name = "pattern";
            this.pattern.ReadOnly = true;
            // 
            // create_time
            // 
            this.create_time.DataPropertyName = "create_time";
            this.create_time.HeaderText = "创建时间";
            this.create_time.MinimumWidth = 8;
            this.create_time.Name = "create_time";
            this.create_time.ReadOnly = true;
            // 
            // change_time
            // 
            this.change_time.DataPropertyName = "change_time";
            this.change_time.HeaderText = "修改时间";
            this.change_time.MinimumWidth = 8;
            this.change_time.Name = "change_time";
            this.change_time.ReadOnly = true;
            // 
            // remark
            // 
            this.remark.DataPropertyName = "remark";
            this.remark.HeaderText = "备注";
            this.remark.MinimumWidth = 8;
            this.remark.Name = "remark";
            this.remark.ReadOnly = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label1.Location = new System.Drawing.Point(57, 45);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(130, 24);
            this.label1.TabIndex = 3;
            this.label1.Text = "账户名称：";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.Location = new System.Drawing.Point(105, 99);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(82, 24);
            this.label2.TabIndex = 4;
            this.label2.Text = "密码：";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label3.Location = new System.Drawing.Point(421, 99);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(82, 24);
            this.label3.TabIndex = 5;
            this.label3.Text = "备注：";
            // 
            // tbx_account
            // 
            this.tbx_account.Location = new System.Drawing.Point(193, 41);
            this.tbx_account.Name = "tbx_account";
            this.tbx_account.Size = new System.Drawing.Size(204, 28);
            this.tbx_account.TabIndex = 6;
            // 
            // tbx_password
            // 
            this.tbx_password.Location = new System.Drawing.Point(193, 95);
            this.tbx_password.Name = "tbx_password";
            this.tbx_password.Size = new System.Drawing.Size(204, 28);
            this.tbx_password.TabIndex = 7;
            // 
            // tbx_remark
            // 
            this.tbx_remark.Location = new System.Drawing.Point(509, 95);
            this.tbx_remark.Name = "tbx_remark";
            this.tbx_remark.Size = new System.Drawing.Size(204, 28);
            this.tbx_remark.TabIndex = 8;
            // 
            // Btn_insertUser
            // 
            this.Btn_insertUser.Location = new System.Drawing.Point(781, 29);
            this.Btn_insertUser.Name = "Btn_insertUser";
            this.Btn_insertUser.Size = new System.Drawing.Size(102, 42);
            this.Btn_insertUser.TabIndex = 9;
            this.Btn_insertUser.Text = "添加";
            this.Btn_insertUser.UseVisualStyleBackColor = true;
            this.Btn_insertUser.Click += new System.EventHandler(this.Btn_insertUser_Click);
            // 
            // Btn_update
            // 
            this.Btn_update.Location = new System.Drawing.Point(781, 90);
            this.Btn_update.Name = "Btn_update";
            this.Btn_update.Size = new System.Drawing.Size(102, 42);
            this.Btn_update.TabIndex = 10;
            this.Btn_update.Text = "修改";
            this.Btn_update.UseVisualStyleBackColor = true;
            this.Btn_update.Click += new System.EventHandler(this.Btn_update_Click);
            // 
            // Btn_select
            // 
            this.Btn_select.Location = new System.Drawing.Point(900, 29);
            this.Btn_select.Name = "Btn_select";
            this.Btn_select.Size = new System.Drawing.Size(102, 42);
            this.Btn_select.TabIndex = 11;
            this.Btn_select.Text = "查询";
            this.Btn_select.UseVisualStyleBackColor = true;
            this.Btn_select.Click += new System.EventHandler(this.Btn_select_Click);
            // 
            // Btn_delete
            // 
            this.Btn_delete.Location = new System.Drawing.Point(900, 90);
            this.Btn_delete.Name = "Btn_delete";
            this.Btn_delete.Size = new System.Drawing.Size(102, 42);
            this.Btn_delete.TabIndex = 12;
            this.Btn_delete.Text = "删除";
            this.Btn_delete.UseVisualStyleBackColor = true;
            this.Btn_delete.Click += new System.EventHandler(this.Btn_delete_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label4.Location = new System.Drawing.Point(421, 45);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(82, 24);
            this.label4.TabIndex = 13;
            this.label4.Text = "模式：";
            // 
            // cbx_pattern
            // 
            this.cbx_pattern.FormattingEnabled = true;
            this.cbx_pattern.Items.AddRange(new object[] {
            "顺序播放",
            "随机播放",
            "单曲循环"});
            this.cbx_pattern.Location = new System.Drawing.Point(509, 45);
            this.cbx_pattern.Name = "cbx_pattern";
            this.cbx_pattern.Size = new System.Drawing.Size(204, 26);
            this.cbx_pattern.TabIndex = 14;
            // 
            // PersonMangeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 18F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1067, 729);
            this.Controls.Add(this.cbx_pattern);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.Btn_delete);
            this.Controls.Add(this.Btn_select);
            this.Controls.Add(this.Btn_update);
            this.Controls.Add(this.Btn_insertUser);
            this.Controls.Add(this.tbx_remark);
            this.Controls.Add(this.tbx_password);
            this.Controls.Add(this.tbx_account);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.panel1);
            this.Name = "PersonMangeForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "人员管理";
            this.Load += new System.EventHandler(this.PersonMangeForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dgv_personInfo)).EndInit();
            this.panel1.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dgv_personInfo;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.DataGridViewTextBoxColumn userid;
        private System.Windows.Forms.DataGridViewTextBoxColumn account;
        private System.Windows.Forms.DataGridViewTextBoxColumn password;
        private System.Windows.Forms.DataGridViewTextBoxColumn musicId;
        private System.Windows.Forms.DataGridViewTextBoxColumn pattern;
        private System.Windows.Forms.DataGridViewTextBoxColumn create_time;
        private System.Windows.Forms.DataGridViewTextBoxColumn change_time;
        private System.Windows.Forms.DataGridViewTextBoxColumn remark;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox tbx_account;
        private System.Windows.Forms.TextBox tbx_password;
        private System.Windows.Forms.TextBox tbx_remark;
        private System.Windows.Forms.Button Btn_insertUser;
        private System.Windows.Forms.Button Btn_update;
        private System.Windows.Forms.Button Btn_select;
        private System.Windows.Forms.Button Btn_delete;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.ComboBox cbx_pattern;
    }
}