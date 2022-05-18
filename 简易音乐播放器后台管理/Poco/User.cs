using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MusicPlayer.Poco
{
    public class User
    {
        public int UserId { get; set; }
        public string AccountName { get; set; }
        public string Password { get; set; }
        public int MusicId { get; set; }
        public int Pattern { get; set; }
        public DateTime CreateTime { get; set; }
        public DateTime ChangeTime { get; set; }
        public string Rermark { get; set; }
    }
}
