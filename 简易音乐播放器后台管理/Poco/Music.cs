using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MusicPlayer.Poco
{
    public class Music
    {
        public int MusicId { get; set; }
        public string MusicName { get; set; }
        public string Author { get; set; }
        public string MusicAddress { get; set; }
        public string Image { get; set; }
        public DateTime CreateTime { get; set; }
        public DateTime ChangeTime { get; set; }
        public string Rermark { get; set; }
    }
}
