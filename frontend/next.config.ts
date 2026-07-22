import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  // reactStrictMode: true,
  // devIndicators: false,
  async rewrites() {
    return [
      {
        source: "/api/:path*",
        destination: "http://127.0.0.1:8080/api/:path*", // 백엔드 포트에 맞게 수정
      },
      {
        source: "/api-system/:path*",
        destination: "http://127.0.0.1:8080/:path*", 
      },
    ];
  },
};

export default nextConfig;
