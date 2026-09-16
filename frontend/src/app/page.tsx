import Link from "next/link";

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center pt-20">
      <h1 className="text-5xl font-extrabold text-blue-700 mb-6 text-center">
        Connect with Your Community
      </h1>
      <p className="text-xl text-gray-600 mb-10 max-w-2xl text-center">
        Signal-Sense uses AI to intelligently match volunteers with the right local organizations based on skills, interests, and availability.
      </p>
      
      <div className="flex gap-6">
        <Link href="/register?type=volunteer" className="bg-blue-600 text-white px-8 py-4 rounded-lg text-lg font-bold shadow-lg hover:bg-blue-700 transition">
          I want to Volunteer
        </Link>
        <Link href="/register?type=organization" className="bg-white text-blue-600 border border-blue-600 px-8 py-4 rounded-lg text-lg font-bold shadow-sm hover:bg-gray-50 transition">
          I represent an Organization
        </Link>
      </div>

      <div className="mt-20 grid grid-cols-1 md:grid-cols-3 gap-8 max-w-5xl">
        <div className="bg-white p-6 rounded-xl shadow-md border border-gray-100">
          <h3 className="text-xl font-bold text-gray-800 mb-3">AI Matching</h3>
          <p className="text-gray-600">Our recommendation engine matches your unique skills with organizations that actually need them.</p>
        </div>
        <div className="bg-white p-6 rounded-xl shadow-md border border-gray-100">
          <h3 className="text-xl font-bold text-gray-800 mb-3">Geo-Location</h3>
          <p className="text-gray-600">Find opportunities in your own neighborhood with distance-based sorting and maps.</p>
        </div>
        <div className="bg-white p-6 rounded-xl shadow-md border border-gray-100">
          <h3 className="text-xl font-bold text-gray-800 mb-3">Scheduling</h3>
          <p className="text-gray-600">Seamlessly sync your commitments with integrated calendars and reminders.</p>
        </div>
      </div>
    </div>
  );
}
