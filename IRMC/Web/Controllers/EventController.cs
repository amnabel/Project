using Data;

using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web;
using System.Web.Mvc;

using Service;
using Web.Models;
using System.Web.Helpers;

namespace Web.Controllers
{

    public class EventController : Controller
    {
        
        EventService fs = new EventService();
        UserService userSe = new UserService();
        DateTime localDate = DateTime.Now;
        Model2 ctx = new Model2();
        public  _event eventt;
        // GET: Event
        public ActionResult Index()
        {
            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/");
            var request = new RestRequest(Method.GET);

            request.AddHeader("Content-type", "application/json");


            IRestResponse<List<EventViewModel>> u = client.Execute<List<EventViewModel>>(request);

            return View(u.Data);
        }
        [HttpPost]
        public ActionResult Index(string searchString)
        {

            List<EventViewModel> LisetOFF = new List<EventViewModel>();
            IEnumerable<_event> offers = fs.FindByName(searchString);

            foreach (var A in offers)
            {
                EventViewModel FVM = new EventViewModel();

               
                FVM.title = A.title;
                FVM.description = A.description;
                FVM.startDate = A.startDate;
                FVM.endDate = A.endDate;
                FVM.capacity = A.capacity;
                FVM.cat = A.cat;
                FVM.startDate = localDate;
                FVM.image = A.image;
                LisetOFF.Add(FVM);
            }
            return View(LisetOFF);
        }

        // GET: Event/Details/5
        public ActionResult Details(int id)
        {
            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/one?idEv="+ id);
            var request = new RestRequest(Method.GET);

            request.AddHeader("Content-type", "application/json");


            IRestResponse<List<EventViewModel>> u = client.Execute<List<EventViewModel>>(request);


            return View(u.Data[0]);
        }

        // GET: Event/Create
        public ActionResult Create()
        {
            return View("Create");
        }


        // POST: Event/Create
        [HttpPost]
        public ActionResult Create(_event ev)
        {

            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080/IRMCJEE-web/IRMC/event/");

            Client.PostAsJsonAsync<_event>("", ev).ContinueWith((postTask) => postTask.Result.IsSuccessStatusCode);
            return RedirectToAction("Index");
        }

        // GET: Formation/Edit/5
        public ActionResult Edit(long id)
        {
            int idev = (int)(long)id;
            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/one?idEv=" + idev);
            var request = new RestRequest(Method.GET);

            request.AddHeader("Content-type", "application/json");


            IRestResponse<List<_event>> u = client.Execute<List<_event>>(request);


            eventt = u.Data[0];


            EventViewModel FVM = new EventViewModel();

            FVM.id_Ev = eventt.id_Ev;
            FVM.title = eventt.title;
            FVM.description = eventt.description;
            FVM.startDate = eventt.startDate;
            FVM.endDate = eventt.endDate;
            FVM.capacity = eventt.capacity;
            FVM.cat = eventt.cat;
            FVM.startDate = localDate;
            FVM.image = eventt.image;


            if (FVM == null)
            {
                return HttpNotFound();
            }
            return View(FVM);
        }

        // POST: Formation/Edit/5
        [HttpPost]
        public ActionResult Edit(long id, EventViewModel FVM)
        {



            int idev = (int)(long)id;
            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/one?idEv=" + idev);
            var request = new RestRequest(Method.GET);

            request.AddHeader("Content-type", "application/json");


            IRestResponse<List<_event>> u = client.Execute<List<_event>>(request);


            eventt = u.Data[0];
            // eventt = fs.GetById(id);

            // combinaison entre Model et view 

            eventt.id_Ev = idev;
            eventt.title= FVM.title;
            eventt.description= FVM.description;
            eventt.startDate=FVM.startDate ;
            eventt.endDate=FVM.endDate;
            eventt.capacity= FVM.capacity ;
            eventt.cat = FVM.cat;
            eventt.startDate= FVM.startDate ;
            eventt.image=FVM.image ;


            fs.Update(eventt);
            fs.Commit();
            return RedirectToAction("Index");
        }


        [HttpGet]
        public ActionResult Delete(int id)
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080/IRMCJEE-web/IRMC/");
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("event/one?idEv=" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                ViewBag.result = response.Content.ReadAsAsync<EventViewModel>().Result;
                return View(ViewBag.result);
            }
            else
            {
                ViewBag.result = "error";
            }
            return View("Delete");
        }
        // event/Delete/id
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080/IRMCJEE-web/IRMC/");
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.DeleteAsync("event/delete?id=" + id).Result;
            return RedirectToAction("Index") ;
        }
      
        public ActionResult Par(int id)
        {
      
            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/one?idEv=" +id);
            var request = new RestRequest(Method.GET);

            request.AddHeader("Content-type", "application/json");


            IRestResponse<List<_event>> u = client.Execute<List<_event>>(request);


            eventt = u.Data[0];

            fs.Participer(eventt.id_Ev);
            Data.user uss = new Data.user();
            IEnumerable<Data.user> ev = userSe.FindById(1);
            foreach (Data.user r in ev)
            {

                if (r.id == 1)
                {
                    uss = r;
                    break;
                }
                break;
            }


            fs.SendEmail(uss);
            return RedirectToAction("Index");
        }

        public ActionResult Chart()
        {
            var context = new Model2();
            var CountN = context._event.SqlQuery("Select * from event where nbPart > 1").Count();
            var CountT = context._event.SqlQuery("Select * from event where nbPart < 1").Count();

            new Chart(width: 800, height: 200).AddSeries(chartType: "pie", xValue: new[] { "capacity > 13", "capacity < 13" }, yValues: new[] { CountT, CountN }).Write("png");
            return View("Chart");
        }
    }
}
