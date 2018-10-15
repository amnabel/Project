using Data.Infrastructure;
using Newtonsoft.Json;
using ServicePattern;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;

namespace Service
{
    public class EventService : Service<Data._event>
    {
        UserService u = new UserService();

        private static IDatabaseFactory dbf = new DatabaseFactory();
        private static IUnitOfWork ut = new UnitOfWork(dbf);

        public EventService() : base(ut)
        {

        }
        public IEnumerable<Data._event> FindByName(string nameIns)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create
            ("http://localhost:18080/IRMCJEE-web/IRMC/event/");
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

            var objs = JsonConvert.DeserializeObject<List<Data._event>>(content);
            List<Data._event> liste = new List<Data._event>();
            foreach (Data._event r in objs)
            {
                Data._event ins = new Data._event();
                if (r.title.Equals(nameIns))
                {
                    ins = r;
                    liste.Add(ins);
                }
            }
            return liste;
        }
        public IEnumerable<Data._event> FindById(int id)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create
            ("http://localhost:18080/IRMCJEE-web/IRMC/event/");
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

            var objs = JsonConvert.DeserializeObject<List<Data._event>>(content);
            List<Data._event> liste = new List<Data._event>();
            foreach (Data._event r in objs)
            {
                Data._event ins = new Data._event();
                if (r.id_Ev==id)
                {
                    ins = r;
                    liste.Add(ins);
                }
            }
            return liste;
        }

        public void SendEmail(Data.user user)
        {

            using (MailMessage mm = new MailMessage("irmc.pidev@gmail.com", user.email))
            {
                mm.Subject = "Event details";
                string body = "Hello " + user.username + ",";
                body += "<br /><br />You will be with us in this event";
                body += "<br /><br />Thanks";
                mm.Body = body;
                mm.IsBodyHtml = true;
                SmtpClient smtp = new SmtpClient();
                smtp.Host = "smtp.gmail.com";
                smtp.EnableSsl = true;
                NetworkCredential NetworkCred = new NetworkCredential("irmc.pidev@gmail.com", "esprit2018");
                smtp.UseDefaultCredentials = true;
                smtp.Credentials = NetworkCred;
                smtp.Port = 587;
                smtp.Send(mm);
            }




        }



        public void Participer(int id)
        {

            Data._event ins = new Data._event();
            Data.user uss = new Data.user();
            IEnumerable<Data._event> eve = FindById(id);
            foreach (Data._event r in eve)
            {
               
                if (r.id_Ev == id)
                {
                    ins = r;
                    break;
                }
                break;
            }
      
           
           
            ins.capacity--;
            ins.nbPart++;
            ins.user_id = 1;
            ins.cat = Domain.Entity.category.cycleDeConferences;
            Update(ins);
            Commit();

        }


    }

}
