using Data.Infrastructure;
using Newtonsoft.Json;
using ServicePattern;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Service
{
   public class UserService : Service<Data.user>
    {
        private static IDatabaseFactory dbf = new DatabaseFactory();
        private static IUnitOfWork ut = new UnitOfWork(dbf);

        public UserService() : base(ut)
        {

        }
        public IEnumerable<Data.user> FindById(int id)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create
               ("http://localhost:18080/IRMCJEE-web/IRMC/user/");
            request.Method = "GET";
            request.AutomaticDecompression = DecompressionMethods.Deflate | DecompressionMethods.GZip;
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            string content = string.Empty;
            using (Stream stream = response.GetResponseStream())
            {
                using (StreamReader sr = new StreamReader(stream))
                {
                    content = sr.ReadToEnd();
                }
            }

            var objs = JsonConvert.DeserializeObject<List<Data.user>>(content);
            List<Data.user> liste = new List<Data.user>();
            foreach (Data.user r in objs)
            {
                Data.user ins = new Data.user();
                if (r.id == id)
                {
                    ins = r;
                    liste.Add(ins);
                }
            }
            return liste;
        }
    }
}
