FROM node:24-alpine AS base

FROM base AS builder
# Install git and clone the repository
RUN apk add --no-cache git
RUN git clone https://github.com/Nithin0620/PasswordStrengthChecker.git /repo
WORKDIR /repo/frontend

RUN npm ci
RUN npm run build

FROM base AS runner
WORKDIR /app

ENV NODE_ENV=production
ENV PORT=3000
ENV HOSTNAME="0.0.0.0"

COPY --from=builder /repo/frontend/public ./public
COPY --from=builder /repo/frontend/.next/standalone ./
COPY --from=builder /repo/frontend/.next/static ./.next/static

EXPOSE 3000

CMD ["node", "server.js"]
