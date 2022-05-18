
namespace MusicPlayer
{
    partial class MusicMangeForm
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
            this.panel1 = new System.Windows.Forms.Panel();
            this.dgv_music = new System.Windows.Forms.DataGridView();
            this.musicId = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.name = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.author = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.address = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.img = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.createTime = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.changeTime = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.remark = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.label4 = new System.Windows.Forms.Label();
            this.Btn_delete = new System.Windows.Forms.Button();
            this.Btn_select = new System.Windows.Forms.Button();
            this.Btn_update = new System.Windows.Forms.Button();
            this.Btn_insertUser = new System.Windows.Forms.Button();
            this.tbx_image = new System.Windows.Forms.TextBox();
            this.tbx_address = new System.Windows.Forms.TextBox();
            this.tbx_musicName = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.tbx_author = new System.Windows.Forms.TextBox();
            this.tbx_remark = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgv_music)).BeginInit();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.dgv_music);
            this.panel1.Location = new System.Drawing.Point(12, 207);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1185, 554);
            this.panel1.TabIndex = 0;
            // 
            // dgv_music
            // 
            this.dgv_music.AllowUserToAddRows = false;
            this.dgv_music.AllowUserToDeleteRows = false;
            this.dgv_music.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgv_music.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgv_music.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.musicId,
            this.name,
            this.author,
            this.address,
            this.img,
            this.createTime,
            this.changeTime,
            this.remark});
            this.dgv_music.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgv_music.Location = new System.Drawing.Point(0, 0);
            this.dgv_music.Name = "dgv_music";
            this.dgv_music.ReadOnly = true;
            this.dgv_music.RowHeadersVisible = false;
            this.dgv_music.RowHeadersWidth = 62;
            this.dgv_music.RowTemplate.Height = 30;
            this.dgv_music.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgv_music.Size = new System.Drawing.Size(1185, 554);
            this.dgv_music.TabIndex = 1;
            this.dgv_music.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgv_music_CellClick);
            // 
            // musicId
            // 
            this.musicId.DataPropertyName = "music_id";
            this.musicId.FillWeight = 60F;
            this.musicId.HeaderText = "编号";
            this.musicId.MinimumWidth = 8;
            this.musicId.Name = "musicId";
            this.musicId.ReadOnly = true;
            // 
            // name
            // 
            this.name.DataPropertyName = "name";
            this.name.HeaderText = "歌曲名称";
            this.name.MinimumWidth = 8;
            this.name.Name = "name";
            this.name.ReadOnly = true;
            // 
            // author
            // 
            this.author.DataPropertyName = "author";
            this.author.HeaderText = "演唱者";
            this.author.MinimumWidth = 8;
            this.author.Name = "author";
            this.author.ReadOnly = true;
            // 
            // address
            // 
            this.address.DataPropertyName = "address";
            this.address.HeaderText = "歌曲地址";
            this.address.MinimumWidth = 8;
            this.address.Name = "address";
            this.address.ReadOnly = true;
            // 
            // img
            // 
            this.img.DataPropertyName = "img";
            this.img.HeaderText = "图片地址";
            this.img.MinimumWidth = 8;
            this.img.Name = "img";
            this.img.ReadOnly = true;
            // 
            // createTime
            // 
            this.createTime.DataPropertyName = "create_time";
            this.createTime.HeaderText = "创建时间";
            this.createTime.MinimumWidth = 8;
            this.createTime.Name = "createTime";
            this.createTime.ReadOnly = true;
            // 
            // changeTime
            // 
            this.changeTime.DataPropertyName = "change_time";
            this.changeTime.HeaderText = "修改时间";
            this.changeTime.MinimumWidth = 8;
            this.changeTime.Name = "changeTime";
            this.changeTime.ReadOnly = true;
            // 
            // remark
            // 
            this.remark.DataPropertyName = "remark";
            this.remark.HeaderText = "备注";
            this.remark.MinimumWidth = 8;
            this.remark.Name = "remark";
            this.remark.ReadOnly = true;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label4.Location = new System.Drawing.Point(482, 39);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(106, 24);
            this.label4.TabIndex = 25;
            this.label4.Text = "演唱者：";
            // 
            // Btn_delete
            // 
            this.Btn_delete.Location = new System.Drawing.Point(1036, 110);
            this.Btn_delete.Name = "Btn_delete";
            this.Btn_delete.Size = new System.Drawing.Size(102, 42);
            this.Btn_delete.TabIndex = 24;
            this.Btn_delete.Text = "删除";
            this.Btn_delete.UseVisualStyleBackColor = true;
            this.Btn_delete.Click += new System.EventHandler(this.Btn_delete_Click);
            // 
            // Btn_select
            // 
            this.Btn_select.Location = new System.Drawing.Point(1036, 49);
            this.Btn_select.Name = "Btn_select";
            this.Btn_select.Size = new System.Drawing.Size(102, 42);
            this.Btn_select.TabIndex = 23;
            this.Btn_select.Text = "查询";
            this.Btn_select.UseVisualStyleBackColor = true;
            this.Btn_select.Click += new System.EventHandler(this.Btn_select_Click);
            // 
            // Btn_update
            // 
            this.Btn_update.Location = new System.Drawing.Point(917, 110);
            this.Btn_update.Name = "Btn_update";
            this.Btn_update.Size = new System.Drawing.Size(102, 42);
            this.Btn_update.TabIndex = 22;
            this.Btn_update.Text = "修改";
            this.Btn_update.UseVisualStyleBackColor = true;
            this.Btn_update.Click += new System.EventHandler(this.Btn_update_Click);
            // 
            // Btn_insertUser
            // 
            this.Btn_insertUser.Location = new System.Drawing.Point(917, 49);
            this.Btn_insertUser.Name = "Btn_insertUser";
            this.Btn_insertUser.Size = new System.Drawing.Size(102, 42);
            this.Btn_insertUser.TabIndex = 21;
            this.Btn_insertUser.Text = "添加";
            this.Btn_insertUser.UseVisualStyleBackColor = true;
            this.Btn_insertUser.Click += new System.EventHandler(this.Btn_insertUser_Click);
            // 
            // tbx_image
            // 
            this.tbx_image.Location = new System.Drawing.Point(581, 89);
            this.tbx_image.Name = "tbx_image";
            this.tbx_image.Size = new System.Drawing.Size(204, 28);
            this.tbx_image.TabIndex = 20;
            // 
            // tbx_address
            // 
            this.tbx_address.Location = new System.Drawing.Point(230, 89);
            this.tbx_address.Name = "tbx_address";
            this.tbx_address.Size = new System.Drawing.Size(204, 28);
            this.tbx_address.TabIndex = 19;
            // 
            // tbx_musicName
            // 
            this.tbx_musicName.Location = new System.Drawing.Point(230, 35);
            this.tbx_musicName.Name = "tbx_musicName";
            this.tbx_musicName.Size = new System.Drawing.Size(204, 28);
            this.tbx_musicName.TabIndex = 18;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label3.Location = new System.Drawing.Point(458, 93);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(130, 24);
            this.label3.TabIndex = 17;
            this.label3.Text = "图片地址：";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.Location = new System.Drawing.Point(94, 89);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(130, 24);
            this.label2.TabIndex = 16;
            this.label2.Text = "歌曲地址：";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label1.Location = new System.Drawing.Point(94, 39);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(130, 24);
            this.label1.TabIndex = 15;
            this.label1.Text = "歌曲名称：";
            // 
            // tbx_author
            // 
            this.tbx_author.Location = new System.Drawing.Point(581, 35);
            this.tbx_author.Name = "tbx_author";
            this.tbx_author.Size = new System.Drawing.Size(204, 28);
            this.tbx_author.TabIndex = 26;
            // 
            // tbx_remark
            // 
            this.tbx_remark.Location = new System.Drawing.Point(230, 143);
            this.tbx_remark.Name = "tbx_remark";
            this.tbx_remark.Size = new System.Drawing.Size(204, 28);
            this.tbx_remark.TabIndex = 28;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label5.Location = new System.Drawing.Point(142, 143);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(82, 24);
            this.label5.TabIndex = 27;
            this.label5.Text = "备注：";
            // 
            // MusicMangeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 18F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1206, 774);
            this.Controls.Add(this.tbx_remark);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.tbx_author);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.Btn_delete);
            this.Controls.Add(this.Btn_select);
            this.Controls.Add(this.Btn_update);
            this.Controls.Add(this.Btn_insertUser);
            this.Controls.Add(this.tbx_image);
            this.Controls.Add(this.tbx_address);
            this.Controls.Add(this.tbx_musicName);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.panel1);
            this.Name = "MusicMangeForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "音乐管理";
            this.Load += new System.EventHandler(this.MusicMangeForm_Load);
            this.panel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgv_music)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.DataGridView dgv_music;
        private System.Windows.Forms.DataGridViewTextBoxColumn musicId;
        private System.Windows.Forms.DataGridViewTextBoxColumn name;
        private System.Windows.Forms.DataGridViewTextBoxColumn author;
        private System.Windows.Forms.DataGridViewTextBoxColumn address;
        private System.Windows.Forms.DataGridViewTextBoxColumn img;
        private System.Windows.Forms.DataGridViewTextBoxColumn createTime;
        private System.Windows.Forms.DataGridViewTextBoxColumn changeTime;
        private System.Windows.Forms.DataGridViewTextBoxColumn remark;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button Btn_delete;
        private System.Windows.Forms.Button Btn_select;
        private System.Windows.Forms.Button Btn_update;
        private System.Windows.Forms.Button Btn_insertUser;
        private System.Windows.Forms.TextBox tbx_image;
        private System.Windows.Forms.TextBox tbx_address;
        private System.Windows.Forms.TextBox tbx_musicName;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox tbx_author;
        private System.Windows.Forms.TextBox tbx_remark;
        private System.Windows.Forms.Label label5;
    }
}