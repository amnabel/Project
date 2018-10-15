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
using ASP.Models;

namespace ASP.Controllers
{

    public class EventController : Controller
    {
        EventService fs = new EventService();
        DateTime localDate = DateTime.Now;
        Model2 ctx = new Model2();
        public  _event eventt;
        // GET: Event
        public ActionResult Index()
        {
            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/");
            var request = new RestRequest(Method.GET);

            request.AddHeader("Content-type", "application/json");


            IRestResponse<List<_event>> u = client.Execute<List<_event>>(request);

            return View(u.Data);
        }

        // GET: Event/Details/5
        public ActionResult Details(int id)
        {
            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/one?idEv="+ id);
            var request = new RestRequest(Method.GET);

            request.AddHeader("Content-type", "application/json");


            IRestResponse<List<_event>> u = client.Execute<List<_event>>(request);


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
        public ActionResult Edit(int id)
        {
            
            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/one?idEv=" + id);
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
        public ActionResult Edit(int id, EventViewModel FVM)
        {

            var client = new RestClient("http://localhost:18080/IRMCJEE-web/IRMC/event/one?idEv=" + id);
            var request = new RestRequest(Method.GET);

            request.AddHeader("Content-type", "application/json");


            IRestResponse<List<_event>> u = client.Execute<List<_event>>(request);


            eventt = u.Data[0];

            // combinaison entre Model et view 

            FVM.id_Ev = eventt.id_Ev;
            FVM.title = eventt.title;
            FVM.description = eventt.description;
            FVM.startDate = eventt.startDate;
            FVM.endDate = eventt.endDate;
            FVM.capacity = eventt.capacity;
            FVM.cat = eventt.cat;
            FVM.startDate = localDate;
            FVM.image = eventt.image;


            fs.Update(eventt);
            fs.Commit();
            return View();
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
                ViewBag.result = response.Content.ReadAsAsync<_event>().Result;
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
    }
}
