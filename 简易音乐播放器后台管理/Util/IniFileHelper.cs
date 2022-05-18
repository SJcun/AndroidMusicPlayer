using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace MusicPlayer
{

    /// <summary>
    /// ini文件读取工具类
    /// </summary>
    public class IniFileHelper
    {
        /// <summary>
        /// ini文件地址
        /// </summary>
        private string FilePath { get; set; }

        // 返回0表示失败，非0为成功
        [DllImport("kernel32", CharSet = CharSet.Auto)]
        private static extern long WritePrivateProfileString(string section, string key, string val, string filePath);

        // 返回取得字符串缓冲区的长度
        [DllImport("kernel32", CharSet = CharSet.Auto)]
        private static extern long GetPrivateProfileString(string section, string key, string strDefault, StringBuilder retVal, int size, string filePath);

        [DllImport("Kernel32.dll", CharSet = CharSet.Auto)]
        public static extern int GetPrivateProfileInt(string section, string key, int nDefault, string filePath);

        /// <summary>
        /// 构造方法
        /// </summary>
        /// <param name="path"></param>
        private IniFileHelper(string path) {
            FilePath = path;
        }

        public static IniFileHelper iniFileHelper = null;

        public static IniFileHelper GetInstance(string path)
        {
            if (iniFileHelper==null)
            {
                iniFileHelper = new IniFileHelper(path);
            }
            return iniFileHelper;
        }

        public string GetIniString(string section, string key)
        {
            StringBuilder temp = new StringBuilder(255);

            long i = GetPrivateProfileString(section, key, "", temp, 255, FilePath);
            return temp.ToString();
        }
    }
}
